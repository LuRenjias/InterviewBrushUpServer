package edu.hnu.service.impl;

import edu.hnu.dao.*;
import edu.hnu.dto.ArticleAbbreviationsDTO;
import edu.hnu.dto.ArticleDTO;
import edu.hnu.entity.*;
import edu.hnu.service.ArticleService;
import edu.hnu.utils.StatusCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

/**
 * (Article)表服务实现类
 *
 * @author lx
 * @since 2024-05-14 10:10:33
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleDao articleDao;

    @Resource
    private ImageDao imageDao;

    @Resource
    private CommentDao commentDao;

    @Resource
    private LikeRecordDao likeRecordDao;

    @Resource
    private HistoryDao historyDao;

    @Value("${info.content-length}")
    private Integer contentLength;

    @Value("${info.recommend-count}")
    private Integer recommendCount;

    @Override
    public int publish(Article article, Integer userId) {
        article.setUserId(userId);
        article.setCreateTime(LocalDateTime.now());
        article.setLikesCount(0);
        article.setFavoritesCount(0);
        article.setViewsCount(0);
        article.setStatus(StatusCode.UNDER_REVIEW.getCode());
        articleDao.insert(article); // 设置了 useGeneratedKeys="true"，插入完后，自动设置了对象的id
        return article.getId();
    }

    private final String imageBasePath = System.getProperty("user.dir") + File.separator
            + "src" + File.separator + "main" + File.separator + "resources" + File.separator
            + "static" + File.separator + "image" + File.separator;

    @Override
    public int delete(Integer articleId, Integer userId) {
        Article article = articleDao.queryById(articleId);
        // 文章不存在
        if (article == null) {
            return 0;
        }
        Integer authorId = article.getUserId();
        // 不是自己的文章
        if (!userId.equals(authorId)) {
            return 0;
        }

        // 删除文章
        articleDao.deleteById(articleId);

        // 删除对应的图片
        List<Image> images = imageDao.queryByCategoryAndCategoryId(0, articleId);
        if (!images.isEmpty()) {
            images.forEach(v -> {
                String imageUrl = v.getImageUrl();
                String imageName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
                String deletePath = imageBasePath + File.separator + imageName;
                File deleteFile = new File(deletePath);
                deleteFile.delete();
            });
        }

        // 删除图片信息数据
        imageDao.deleteByCategoryAndCategoryId(0, articleId);

        return 1;
    }

    @Override
    public List<ArticleAbbreviationsDTO> myList(Integer userId) {
        List<ArticleAbbreviationsDTO> articleAbbreviationsDTOS = articleDao.queryAbbreviationsByUserId(userId);
        // 为空判断
        if (articleAbbreviationsDTOS.isEmpty()) {
            return null;
        }

        constructArticleAbbreviationsDTOs(articleAbbreviationsDTOS);

        return articleAbbreviationsDTOS;
    }

    @Override
    public List<ArticleAbbreviationsDTO> findList() {
        Article article = new Article();
        article.setStatus(StatusCode.APPROVED.getCode());
        long count = articleDao.count(article);

        if (count < recommendCount) {
            List<ArticleAbbreviationsDTO> articleAbbreviationsDTOS = articleDao.listAllAbbreviations(StatusCode.APPROVED.getCode());
            // 为空判断
            if (articleAbbreviationsDTOS.isEmpty()) {
                return null;
            }

            Collections.shuffle(articleAbbreviationsDTOS);
            constructArticleAbbreviationsDTOs(articleAbbreviationsDTOS);
            return articleAbbreviationsDTOS;
        } else {
            Random random = new Random();
            long diff = count - recommendCount;
            int skipCount = random.nextInt((int) (diff + 1));
            List<ArticleAbbreviationsDTO> articleAbbreviationsDTOS = articleDao.listAbbreviationsLimit(skipCount,
                    recommendCount, StatusCode.APPROVED.getCode());
            // 为空判断
            if (articleAbbreviationsDTOS.isEmpty()) {
                return null;
            }

            Collections.shuffle(articleAbbreviationsDTOS);
            constructArticleAbbreviationsDTOs(articleAbbreviationsDTOS);
            return articleAbbreviationsDTOS;
        }
    }

    @Override
    public ArticleDTO detail(Integer articleId, Integer userId) {
        ArticleDTO articleDTO = articleDao.queryDetailById(articleId);
        // 文章不存在
        if (articleDTO == null) {
            return null;
        }

        // 添加到或更新历史记录
        History query = historyDao.queryByUserIdAndArticleId(userId, articleId);
        if (query == null) { // 添加到历史记录
            History history = new History(0, userId, articleId, LocalDateTime.now());
            historyDao.insert(history);
        } else { // 更新历史记录
            query.setVisitTime(LocalDateTime.now());
            historyDao.update(query);
        }

        // 评论数量
        Comment comment = new Comment();
        comment.setCategory(0); // 0表示文章
        comment.setCategoryId(articleId);
        long commentsCount = commentDao.count(comment);
        articleDTO.setCommentsCount((int) commentsCount);

        // 评论列表

        // 图片访问地址
        List<Image> images = imageDao.queryByCategoryAndCategoryId(0, articleId);
        articleDTO.setImages(images);

        // 如果不是自己的文章，则浏览量+1
        if (!articleDTO.getUserId().equals(userId)) {
            Article article = articleDao.queryById(articleId);
            article.setViewsCount(article.getViewsCount() + 1);
            articleDao.update(article);
        }

        return articleDTO;
    }

    @Override
    public int like(Integer articleId, boolean like, Integer userId) {
        Article article = articleDao.queryById(articleId);
        // 文章不存在（查看时文章还在，准备点赞或取消点赞时被原作者删了）
        if (article == null) {
            return 0;
        }

        LikeRecord query = likeRecordDao.queryByUserIdAndArticleId(userId, articleId);
        if (like) { // 点赞
            // 不得重复点赞
            if (query != null) {
                return 2;
            }

            article.setLikesCount(article.getLikesCount() + 1);
            // 添加到点赞记录
            LikeRecord likeRecord = new LikeRecord(0, userId, articleId, LocalDateTime.now());
            likeRecordDao.insert(likeRecord);
        } else { // 取消点赞
            // 不得重复取消点赞
            if (query == null) {
                return 2;
            }

            article.setLikesCount(Math.max(article.getLikesCount() - 1, 0));
            // 从点赞记录中移除
            likeRecordDao.deleteByUserIdAndArticleId(userId, articleId);
        }

        articleDao.update(article);

        return 1;
    }

    @Override
    public List<ArticleAbbreviationsDTO> likeRecordOrHistory(Integer userId, Integer type) {
        List<LikeRecord> likeRecords;
        List<History> history;
        List<Integer> articleIds;

        if (type == 0) { // 0表示点赞记录
            likeRecords = likeRecordDao.queryByUserId(userId);
            articleIds = likeRecords.stream().map(LikeRecord::getArticleId).toList();
        } else { // 1表示历史记录
            history = historyDao.queryByUserId(userId);
            articleIds = history.stream().map(History::getArticleId).toList();
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        for (Integer id : articleIds) {
            stringBuilder.append(id).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(")");
        String ids = stringBuilder.toString().equals(")") ? null : stringBuilder.toString();

        List<ArticleAbbreviationsDTO> articleAbbreviationsDTOS;
        if (type == 0) { // 0表示点赞记录
            articleAbbreviationsDTOS = articleDao.queryLikeInArticleId(ids);
        } else { // 1表示历史记录
            articleAbbreviationsDTOS = articleDao.queryHistoryInArticleId(ids);
        }
        // 为空判断
        if (articleAbbreviationsDTOS.isEmpty()) {
            return null;
        }

        constructArticleAbbreviationsDTOs(articleAbbreviationsDTOS);

        return articleAbbreviationsDTOS;

    }

    @Override
    public List<ArticleAbbreviationsDTO> queryByArticleTitle(String keyword, Integer orderType) {
        List<ArticleAbbreviationsDTO> articleAbbreviationsDTOS = switch (orderType) {
            case 0 -> // 0表示按时间降序
                    articleDao.queryByArticleTitle(keyword, "id", StatusCode.APPROVED.getCode());
            case 1 -> // 1表示按浏览量降序
                    articleDao.queryByArticleTitle(keyword, "views_count", StatusCode.APPROVED.getCode());
            case 2 -> // 2表示按点赞数降序
                    articleDao.queryByArticleTitle(keyword, "likes_count", StatusCode.APPROVED.getCode());
            default -> // 默认按照浏览量降序
                    articleDao.queryByArticleTitle(keyword, "views_count", StatusCode.APPROVED.getCode());
        };
        // 为空判断
        if (articleAbbreviationsDTOS.isEmpty()) {
            return null;
        }

        constructArticleAbbreviationsDTOs(articleAbbreviationsDTOS);

        return articleAbbreviationsDTOS;
    }

    // 查询`image`和`comment`表构造`文章缩略信息`中的`图片地址字段`和`评论数量字段`
    void constructArticleAbbreviationsDTOs(List<ArticleAbbreviationsDTO> articleAbbreviationsDTOS) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        for (ArticleAbbreviationsDTO articleAbbreviationsDTO : articleAbbreviationsDTOS) {
            stringBuilder.append(articleAbbreviationsDTO.getId()).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(")");

        String ids = stringBuilder.toString().equals(")") ? null : stringBuilder.toString();

        List<Image> imageList = imageDao.listIn(0, ids); // 0表示文章
        Map<Integer, List<Image>> imageMap = new HashMap<>();
        for (Image image : imageList) {
            if (!imageMap.containsKey(image.getCategoryId())) {
                List<Image> temp = new ArrayList<>();
                temp.add(image);
                imageMap.put(image.getCategoryId(), temp);
            } else {
                imageMap.get(image.getCategoryId()).add(image);
            }
        }

        List<Comment> commentList = commentDao.listIn(0, ids); // 0表示文章
        Map<Integer, Integer> commentMap = new HashMap<>();
        for (Comment comment : commentList) {
            if (!commentMap.containsKey(comment.getCategoryId())) {
                commentMap.put(comment.getCategoryId(), 1);
            } else {
                commentMap.put(comment.getCategoryId(), commentMap.get(comment.getCategoryId()) + 1);
            }
        }

        if (!articleAbbreviationsDTOS.isEmpty()) {
            articleAbbreviationsDTOS.forEach(v -> {
                v.setImages(imageMap.get(v.getId()));
                Integer commentCount = commentMap.get(v.getId());
                v.setCommentsCount(commentCount == null ? 0 : commentCount);
                String content = v.getContent();
                v.setContent(content.substring(0, contentLength));
            });
        }
    }
}

package edu.hnu.service.impl;

import edu.hnu.dao.ImageDao;
import edu.hnu.entity.Article;
import edu.hnu.dao.ArticleDao;
import edu.hnu.entity.Image;
import edu.hnu.service.ArticleService;
import edu.hnu.utils.StatusCode;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

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
}

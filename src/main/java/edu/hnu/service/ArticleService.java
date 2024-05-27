package edu.hnu.service;

import edu.hnu.dto.ArticleAbbreviationsDTO;
import edu.hnu.dto.ArticleDTO;
import edu.hnu.entity.Article;

import java.util.List;


/**
 * (Article)表服务接口
 *
 * @author lx
 * @since 2024-05-14 10:10:33
 */
public interface ArticleService {

    /**
     * 发布文章.
     */
    int publish(Article article, Integer userId);

    /**
     * 删除文章.
     */
    int delete(Integer articleId, Integer userId);

    /**
     * 我的文章.
     */
    List<ArticleAbbreviationsDTO> myList(Integer userId);

    /**
     * 发现页文章.
     */
    List<ArticleAbbreviationsDTO> findList();

    /**
     * 文章详情.
     */
    ArticleDTO detail(Integer articleId, Integer userId);

    /**
     * 点赞或取消点赞.
     */
    int like(Integer articleId, boolean like, Integer userId);

    List<ArticleAbbreviationsDTO> likeRecordOrHistory(Integer userId, Integer type);
}

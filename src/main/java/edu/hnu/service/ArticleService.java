package edu.hnu.service;

import edu.hnu.entity.Article;


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
}

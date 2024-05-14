package edu.hnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * (Article)实体类
 *
 * @author lx
 * @since 2024-05-14 10:10:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    /**
     * 文章_id
     */
    private Integer id;
    /**
     * 文章_作者的用户_id
     */
    private Integer userId;
    /**
     * 文章_标题
     */
    private String title;
    /**
     * 文章_内容
     */
    private String content;
    /**
     * 文章_创建时间
     */
    private Date createTime;
    /**
     * 文章_点赞数量
     */
    private Integer likesCount;
    /**
     * 文章_收藏数量
     */
    private Integer favoritesCount;
    /**
     * 文章_类别
     */
    private Integer category;
    /**
     * 文章_浏览量
     */
    private Integer viewsCount;
    /**
     * 文章_状态
     */
    private Integer status;
}


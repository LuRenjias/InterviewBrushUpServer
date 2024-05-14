package edu.hnu.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Article)实体类
 *
 * @author lx
 * @since 2024-05-14 10:10:33
 */
public class Article implements Serializable {
    private static final long serialVersionUID = -43730076998306905L;
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Integer getFavoritesCount() {
        return favoritesCount;
    }

    public void setFavoritesCount(Integer favoritesCount) {
        this.favoritesCount = favoritesCount;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Integer viewsCount) {
        this.viewsCount = viewsCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}


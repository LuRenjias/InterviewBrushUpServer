package edu.hnu.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Comment)实体类
 *
 * @author lx
 * @since 2024-05-14 10:12:46
 */
public class Comment implements Serializable {
    private static final long serialVersionUID = 880991555818683850L;
/**
     * 评论_id
     */
    private Integer id;
/**
     * 评论_用户的_id
     */
    private Integer userId;
/**
     * 被评论文章或八股的_id
     */
    private Integer categoryId;
/**
     * 评论_内容
     */
    private String commentContent;
/**
     * 评论_时间
     */
    private Date commentTime;
/**
     * 父评论的_id
     */
    private Integer parentCommentId;
/**
     * 评论_类别
     */
    private Integer category;


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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

}


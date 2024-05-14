package edu.hnu.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (IntegratedQuestion)实体类
 *
 * @author lx
 * @since 2024-05-14 10:13:20
 */
public class IntegratedQuestion implements Serializable {
    private static final long serialVersionUID = -62248199777187563L;
/**
     * 综合题_id
     */
    private Integer id;
/**
     * 问题_所属用户的_id
     */
    private Integer userId;
/**
     * 问题_内容
     */
    private String question;
/**
     * 问题_答案
     */
    private String answer;
/**
     * 问题_类别
     */
    private Integer category;
/**
     * 综合题_重要等级
     */
    private Integer importanceLevel;
/**
     * 综合题_发布时间
     */
    private Date publishTime;
/**
     * 综合题_浏览量
     */
    private Integer viewsCount;
/**
     * 综合题_状态
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getImportanceLevel() {
        return importanceLevel;
    }

    public void setImportanceLevel(Integer importanceLevel) {
        this.importanceLevel = importanceLevel;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
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


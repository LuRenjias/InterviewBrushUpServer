package edu.hnu.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (ChoiceQuestion)实体类
 *
 * @author lx
 * @since 2024-05-14 10:10:54
 */
public class ChoiceQuestion implements Serializable {
    private static final long serialVersionUID = 612802277371932303L;
/**
     * 选择题_id
     */
    private Integer id;
/**
     * 选择题_题干
     */
    private String question;
/**
     * 选择题_选项
     */
    private String options;
/**
     * 选择题_类型
     */
    private String[] type;
/**
     * 选择题_类别
     */
    private Integer category;
/**
     * 选择题_正确选项
     */
    private String correctOption;
/**
     * 选择题_发布时间
     */
    private Date publishTime;
/**
     * 选择题_浏览量
     */
    private Integer viewsCount;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
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

}


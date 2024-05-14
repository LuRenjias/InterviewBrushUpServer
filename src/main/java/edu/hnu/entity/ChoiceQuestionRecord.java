package edu.hnu.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (ChoiceQuestionRecord)实体类
 *
 * @author lx
 * @since 2024-05-14 10:12:18
 */
public class ChoiceQuestionRecord implements Serializable {
    private static final long serialVersionUID = 225345621292921116L;
/**
     * 选择题记录ID
     */
    private Integer id;
/**
     * 用户ID
     */
    private Integer userId;
/**
     * 选择题ID
     */
    private Integer choiceQuestionId;
/**
     * 用户选项
     */
    private String userOption;
/**
     * 作答时间
     */
    private Date choiceTime;


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

    public Integer getChoiceQuestionId() {
        return choiceQuestionId;
    }

    public void setChoiceQuestionId(Integer choiceQuestionId) {
        this.choiceQuestionId = choiceQuestionId;
    }

    public String getUserOption() {
        return userOption;
    }

    public void setUserOption(String userOption) {
        this.userOption = userOption;
    }

    public Date getChoiceTime() {
        return choiceTime;
    }

    public void setChoiceTime(Date choiceTime) {
        this.choiceTime = choiceTime;
    }

}


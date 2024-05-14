package edu.hnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * (ChoiceQuestionRecord)实体类
 *
 * @author lx
 * @since 2024-05-14 10:12:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceQuestionRecord {
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


}


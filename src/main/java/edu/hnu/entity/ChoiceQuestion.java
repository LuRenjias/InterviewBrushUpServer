package edu.hnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * (ChoiceQuestion)实体类
 *
 * @author lx
 * @since 2024-05-14 10:10:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceQuestion {
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
    private String type;
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


}


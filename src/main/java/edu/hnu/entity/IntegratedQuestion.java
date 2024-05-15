package edu.hnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * (IntegratedQuestion)实体类
 *
 * @author lx
 * @since 2024-05-14 10:13:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntegratedQuestion {
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
}


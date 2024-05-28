package edu.hnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
  private LocalDateTime publishTime;
  /**
   * 综合题_浏览量
   */
  private Integer viewsCount;
  /**
   * 综合题_状态
   */
  private Integer status;

  public IntegratedQuestion(Integer userId, String question, String answer, Integer category, Integer importanceLevel, String publishTime, Integer viewsCount, Integer status) {
    this.userId = userId;
    this.question = question;
    this.answer = answer;
    this.category = category;
    this.importanceLevel = importanceLevel;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    this.publishTime = LocalDateTime.parse(publishTime, formatter);
    this.viewsCount = viewsCount;
    this.status = status;
  }
}


package edu.hnu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceQuestionListDTO {
  private Integer category;
  private Long totalNum;
  private Long completedQuestions;
}

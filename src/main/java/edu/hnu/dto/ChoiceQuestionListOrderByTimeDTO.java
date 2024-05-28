package edu.hnu.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceQuestionListOrderByTimeDTO {
  private Integer category;
  private Long totalNum;
  private Long completedQuestions;
  private String choiceTime;
}

package edu.hnu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceQuestionErrorSetDTO {
  private Integer id;
  private String question;
  private Integer category;
  private String choiceTime;
}

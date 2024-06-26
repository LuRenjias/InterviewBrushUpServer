package edu.hnu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceQuestionUnDoneDTO {
  private Boolean flag; //是否做过
  private Integer id;
  private String question;
  private String options;
  private String type;
  private Boolean isCollect;
}

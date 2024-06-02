package edu.hnu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceQuestionDoneDTO {
  private Boolean flag; //是否做过
  private Integer id;
  private String question;
  private String options;
  private String user_option;
  private String type;
  private String answer;
  private Boolean isCollect;
}

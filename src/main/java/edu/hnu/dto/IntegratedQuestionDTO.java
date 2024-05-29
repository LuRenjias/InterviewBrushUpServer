package edu.hnu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntegratedQuestionDTO {
    private Integer id;
    private String question;
    private String userName;
    private Integer importanceLevel;
}

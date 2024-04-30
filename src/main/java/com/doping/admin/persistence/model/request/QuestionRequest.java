package com.doping.admin.persistence.model.request;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionRequest {
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private Integer answer;
}

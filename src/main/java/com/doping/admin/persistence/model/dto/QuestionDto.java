package com.doping.admin.persistence.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionDto {
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}

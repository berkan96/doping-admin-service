package com.doping.admin.persistence.model.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ExamDto {
    private String title;
    private String description;
    private List<QuestionDto> questions;

}

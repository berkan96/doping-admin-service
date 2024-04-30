package com.doping.admin.persistence.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ExamRequest {
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @NotEmpty
    private List<QuestionRequest> questions;
}

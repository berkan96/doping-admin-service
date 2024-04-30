package com.doping.admin.persistence.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;


@Data
@AllArgsConstructor
public class SubmitExamRequest {
    @NotEmpty
    private String studentNumber;
    @NotEmpty
    private String examTitle;
    private Long examId;
    @NotEmpty
    private HashMap<Long, Integer> answers;
}

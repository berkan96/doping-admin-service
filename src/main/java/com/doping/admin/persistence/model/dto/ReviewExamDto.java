package com.doping.admin.persistence.model.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class ReviewExamDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String examTitle;
    private int countCorrentAnswer;
    private int countWrongAnswer;
    private List<AnswerDto> answers;
}

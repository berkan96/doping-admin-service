package com.doping.admin.persistence.model.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ResultDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long examId;
    private String examTitle;
    private Integer correctAnswer;
    private Integer wrongAnswer;
}

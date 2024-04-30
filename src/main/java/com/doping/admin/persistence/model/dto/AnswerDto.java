package com.doping.admin.persistence.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerDto {
    public long questionId;
    public int studentAnswer;
    public boolean isCorrect;
}

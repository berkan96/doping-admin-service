package com.doping.admin.converter;

import com.doping.admin.persistence.entity.Answer;
import com.doping.admin.persistence.model.dto.AnswerDto;

public class AnswerConverter {
    public static AnswerDto entityToDto(Answer answer) {
        return AnswerDto.builder()
                .studentAnswer(answer.getStudentAnswer())
                .questionId(answer.getQuestionId())
                .isCorrect(answer.getIsCorrectAnswer())
                .build();
    }
}

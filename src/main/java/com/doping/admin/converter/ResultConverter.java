package com.doping.admin.converter;

import com.doping.admin.persistence.entity.ExamResult;
import com.doping.admin.persistence.model.dto.ResultDto;

public class ResultConverter {
    public static ResultDto entityToDto(ExamResult examResult) {
        return ResultDto.builder()
                .examId(examResult.getExamId())
                .examTitle(examResult.getExamTitle())
                .correctAnswer(examResult.getCorrectAnswers())
                .wrongAnswer(examResult.getWrongAnswers())
                .build();
    }
}

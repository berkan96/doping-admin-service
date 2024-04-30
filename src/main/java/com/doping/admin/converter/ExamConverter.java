package com.doping.admin.converter;

import com.doping.admin.persistence.entity.Exam;
import com.doping.admin.persistence.model.request.ExamRequest;
import com.doping.admin.persistence.model.dto.ExamDto;
import org.springframework.stereotype.Component;


@Component
public class ExamConverter {

    public Exam toExamEntity(ExamRequest request) {
        Exam entity = new Exam();
        entity.setDescription(request.getDescription());
        entity.setTitle(request.getTitle());
        entity.setQuestions(request.getQuestions().stream().map(QuestionConverter::toQuestionEntity).toList());
        return entity;
    }

    public static ExamDto entityToDto(Exam exam) {
        return ExamDto.builder()
                .title(exam.getTitle())
                .description(exam.getDescription())
                .questions(exam.getQuestions().stream().map(QuestionConverter::entityToDto).toList())
                .build();
    }
}

package com.doping.admin.converter;

import com.doping.admin.persistence.entity.Question;
import com.doping.admin.persistence.model.request.QuestionRequest;
import com.doping.admin.persistence.model.dto.QuestionDto;

public class QuestionConverter {

    public static Question toQuestionEntity(QuestionRequest request) {
        Question entity = new Question();
        entity.setQuestion(request.getQuestion());
        entity.setOption1(request.getOption1());
        entity.setOption2(request.getOption2());
        entity.setOption3(request.getOption3());
        entity.setOption4(request.getOption4());
        entity.setAnswer(request.getAnswer());
        return entity;
    }

    public static QuestionDto entityToDto(Question question) {
        return QuestionDto.builder()
                .question(question.getQuestion())
                .option1(question.getOption1())
                .option2(question.getOption2())
                .option3(question.getOption3())
                .option4(question.getOption4())
                .build();
    }
}

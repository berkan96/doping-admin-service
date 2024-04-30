package com.doping.admin.service;

import com.doping.admin.converter.ExamConverter;
import com.doping.admin.converter.QuestionConverter;
import com.doping.admin.exception.NoSuchExamException;
import com.doping.admin.persistence.entity.Exam;
import com.doping.admin.persistence.enums.ExamStatus;
import com.doping.admin.persistence.model.request.ExamRequest;
import com.doping.admin.persistence.model.dto.ExamDto;
import com.doping.admin.persistence.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;
    private final ExamConverter examConverter;
    public ExamDto saveExam(ExamRequest request) {
        if (Boolean.TRUE.equals(examRepository.existsByTitle(request.getTitle()))) {
            throw new RuntimeException();
        }
        Exam entity = examConverter.toExamEntity(request);
        examRepository.save(entity);
        entity.setStatus(ExamStatus.ACTIVE);
        return ExamConverter.entityToDto(entity);
    }

    public void deleteExam(Long id) {
        Exam entity = examRepository.findById(id).orElseThrow(NoSuchExamException::new);
        entity.setStatus(ExamStatus.PASSIVE);
        examRepository.save(entity);
    }

    public ExamDto updateExam(ExamRequest request, Long id) {
        Exam entity = examRepository.findById(id).orElseThrow(NoSuchExamException::new);

        if (Objects.nonNull(request.getTitle())) {
            entity.setTitle(request.getTitle());
        }
        if (Objects.nonNull(request.getDescription())) {
            entity.setTitle(request.getDescription());
        }
        if (Objects.nonNull(request.getQuestions())) {
            entity.setQuestions(request.getQuestions().stream().map(QuestionConverter::toQuestionEntity).toList());
        }
        return ExamConverter.entityToDto(examRepository.save(entity));
    }

    public ExamDto getExam(Long id) {
        return ExamConverter.entityToDto(examRepository.findById(id).orElseThrow(NoSuchExamException::new));
    }
}

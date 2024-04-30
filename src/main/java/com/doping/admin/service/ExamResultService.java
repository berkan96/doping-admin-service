package com.doping.admin.service;

import com.doping.admin.converter.AnswerConverter;
import com.doping.admin.converter.ResultConverter;
import com.doping.admin.exception.NoSuchExamException;
import com.doping.admin.exception.NoSuchStudentException;
import com.doping.admin.persistence.entity.*;
import com.doping.admin.persistence.model.dto.ResultDto;
import com.doping.admin.persistence.model.dto.ReviewExamDto;
import com.doping.admin.persistence.model.request.SubmitExamRequest;
import com.doping.admin.persistence.repository.ExamRepository;
import com.doping.admin.persistence.repository.ExamResultRepository;
import com.doping.admin.persistence.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamResultService {
    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;
    private final ExamResultRepository examResultRepository;

    @Transactional
    public void submitExam(SubmitExamRequest request) {
        studentRepository.findByStudentNumber(request.getStudentNumber())
                .orElseThrow(NoSuchStudentException::new);
        Exam exam = examRepository.findById(request.getExamId())
                .orElseThrow(NoSuchExamException::new);

        List<Answer> answers = prepareAnswerEntities(exam, request);
        ExamResult result = prepareExamResult(request, answers);
        examResultRepository.save(result);
    }

    @Cacheable("results")
    public List<ResultDto> getAllExamResultByStudentNumber(String studentNumber) {
        studentRepository.findByStudentNumber(studentNumber).orElseThrow(NoSuchStudentException::new);
        List<ExamResult> answers = examResultRepository.findAllByStudentNumber(studentNumber);
        return answers.stream().map(ResultConverter::entityToDto).toList();
    }

    @Cacheable("result")
    public ResultDto getExamResultByStudentNumberAndExamId(String studentNumber, Long examId) {
        studentRepository.findByStudentNumber(studentNumber).orElseThrow(NoSuchStudentException::new);
        ExamResult examResult = examResultRepository.findByStudentNumberAndAndExamId(studentNumber, examId)
                .orElseThrow(RuntimeException::new);
        return ResultConverter.entityToDto(examResult);
    }


    @Cacheable("reviewExam")
    public ReviewExamDto reviewSolvedExam(String studentNumber, Long examId) {
        ExamResult examResult = examResultRepository.findByStudentNumberAndAndExamId(studentNumber, examId)
                .orElseThrow(RuntimeException::new);
        List<Answer> answers = examResult.getAnswers();
        return prepareReviewExamDto(examResult, answers);
    }

    private List<Answer> prepareAnswerEntities(Exam exam, SubmitExamRequest request) {
        HashMap<Long, Integer> studentAnswers = request.getAnswers();
        List<Answer> answers = new ArrayList<>();

        for (Question question : exam.getQuestions()) {
            Answer answer = new Answer();
            answer.setIsCorrectAnswer(false);
            answer.setQuestionId(question.getId());
            answer.setStudentAnswer(studentAnswers.get(question.getId()));
            if (studentAnswers.get(question.getId()).equals(question.getAnswer())) {
                answer.setIsCorrectAnswer(true);
            }
            answers.add(answer);
        }
        return answers;
    }

    private ExamResult prepareExamResult(SubmitExamRequest request, List<Answer> answers) {
        ExamResult result = new ExamResult();
        result.setExamId(request.getExamId());
        result.setStudentNumber(request.getStudentNumber());
        int countCorrect = answers.stream().filter(Answer::getIsCorrectAnswer).toList().size();
        result.setCorrectAnswers(countCorrect);
        result.setExamTitle(request.getExamTitle());
        result.setWrongAnswers(answers.size() - countCorrect);
        result.setAnswers(answers);
        return result;
    }

    private ReviewExamDto prepareReviewExamDto(ExamResult result, List<Answer> answers) {
        return ReviewExamDto.builder()
                .examTitle(result.getExamTitle())
                .countCorrentAnswer(result.getCorrectAnswers())
                .countWrongAnswer(result.getWrongAnswers())
                .answers(answers.stream().map(AnswerConverter::entityToDto).toList())
                .build();
    }
}

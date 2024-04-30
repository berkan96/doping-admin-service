package com.doping.admin.service;

import com.doping.admin.exception.NoSuchExamException;
import com.doping.admin.exception.NoSuchStudentException;
import com.doping.admin.persistence.entity.Answer;
import com.doping.admin.persistence.entity.Exam;
import com.doping.admin.persistence.entity.ExamResult;
import com.doping.admin.persistence.entity.Student;
import com.doping.admin.persistence.model.dto.ResultDto;
import com.doping.admin.persistence.model.dto.ReviewExamDto;
import com.doping.admin.persistence.model.request.SubmitExamRequest;
import com.doping.admin.persistence.repository.ExamRepository;
import com.doping.admin.persistence.repository.ExamResultRepository;
import com.doping.admin.persistence.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExamResultServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ExamRepository examRepository;

    @Mock
    private ExamResultRepository examResultRepository;

    @InjectMocks
    private ExamResultService examResultService;

    @Test
    void testSubmitExam_WithNonExistingStudent() {
        SubmitExamRequest request = mock(SubmitExamRequest.class);
        when(studentRepository.findByStudentNumber(request.getStudentNumber()))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchStudentException.class, () -> examResultService.submitExam(request));
        verify(examRepository, never()).findById(anyLong());
        verify(examResultRepository, never()).save(any());
    }

    @Test
    void testSubmitExam_WithNonExistingExam() {
        SubmitExamRequest request = mock(SubmitExamRequest.class);
        Exam exam = mock(Exam.class);
        when(studentRepository.findByStudentNumber(request.getStudentNumber()))
                .thenReturn(Optional.of(new Student()));
        when(examRepository.findById(exam.getId()))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchExamException.class, () -> examResultService.submitExam(request));
        verify(examResultRepository, never()).save(any());
    }

    @Test
    void testGetAllExamResultByStudentNumber_WithValidStudent() {
        when(studentRepository.findByStudentNumber("543543534543"))
                .thenReturn(Optional.of(new Student()));
        when(examResultRepository.findAllByStudentNumber("543543534543"))
                .thenReturn(List.of(new ExamResult()));

        List<ResultDto> resultDtos = examResultService.getAllExamResultByStudentNumber("543543534543");
        assertNotNull(resultDtos);
        assertEquals(1, resultDtos.size());
    }

    @Test
    void testGetAllExamResultByStudentNumber_WithNonExistingStudent() {

        when(studentRepository.findByStudentNumber("543543534543"))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchStudentException.class, () -> examResultService.getAllExamResultByStudentNumber("543543534543"));
    }

    @Test
    void testGetExamResultByStudentNumberAndExamId_WithValidData() {
        String studentNumber = "543543534543";
        Long examId = 1L;
        when(studentRepository.findByStudentNumber(studentNumber))
                .thenReturn(Optional.of(new Student()));
        ExamResult examResult = new ExamResult();
        when(examResultRepository.findByStudentNumberAndAndExamId(studentNumber, examId))
                .thenReturn(Optional.of(examResult));

        ResultDto resultDto = examResultService.getExamResultByStudentNumberAndExamId(studentNumber, examId);
        assertNotNull(resultDto);
    }

    @Test
    void testGetExamResultByStudentNumberAndExamId_WithNonExistingStudent() {
        String studentNumber = "543543534543";
        Long examId = 1L;
        when(studentRepository.findByStudentNumber(studentNumber))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchStudentException.class, () -> examResultService.getExamResultByStudentNumberAndExamId(studentNumber, examId));
    }

    @Test
    void testGetExamResultByStudentNumberAndExamId_WithNonExistingExamResult() {
        String studentNumber = "543543534543";
        Long examId = 2L;
        when(studentRepository.findByStudentNumber(studentNumber))
                .thenReturn(Optional.of(new Student()));
        when(examResultRepository.findByStudentNumberAndAndExamId(studentNumber, examId))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> examResultService.getExamResultByStudentNumberAndExamId(studentNumber, examId));
    }

    @Test
    void testReviewSolvedExam_WithValidData() {
        String studentNumber = "543543534543";
        Long examId = 1L;
        ExamResult examResult = mock(ExamResult.class);
        List<Answer> answers = List.of(new Answer(), new Answer());
        examResult.setAnswers(answers);
        when(examResultRepository.findByStudentNumberAndAndExamId(studentNumber, examId))
                .thenReturn(Optional.of(examResult));
        ReviewExamDto reviewExamDto = examResultService.reviewSolvedExam(studentNumber, examId);
        assertNotNull(reviewExamDto);
    }
}

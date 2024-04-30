package com.doping.admin.service;

import com.doping.admin.converter.ExamConverter;
import com.doping.admin.exception.NoSuchExamException;
import com.doping.admin.persistence.entity.Exam;
import com.doping.admin.persistence.enums.ExamStatus;
import com.doping.admin.persistence.model.dto.ExamDto;
import com.doping.admin.persistence.model.request.ExamRequest;
import com.doping.admin.persistence.repository.ExamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ExamServiceTest {

    @Mock
    private ExamConverter examConverter;
    @Mock
    private ExamRepository examRepository;
    @InjectMocks
    private ExamService examService;

    @Test
    void testSaveExam_WithNonExistingTitle() {
        ExamRequest request = mock(ExamRequest.class);
        Exam exam = mock(Exam.class);
        when(examRepository.existsByTitle(request.getTitle())).thenReturn(false);
        when(examConverter.toExamEntity(request)).thenReturn(exam);
        when(examRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        assertDoesNotThrow(() -> examService.saveExam(request));
    }

    @Test
    void testSaveExam_WithExistingTitle() {
        ExamRequest request = mock(ExamRequest.class);
        when(examRepository.existsByTitle(request.getTitle())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> examService.saveExam(request));
        verify(examRepository, never()).save(any());
    }

    @Test
    void testDeleteExam_WithExistingId() {
        Long id = 1L;
        Exam exam = new Exam();
        when(examRepository.findById(id)).thenReturn(Optional.of(exam));
        when(examRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        assertDoesNotThrow(() -> examService.deleteExam(id));
        assertEquals(ExamStatus.PASSIVE, exam.getStatus());
    }

    @Test
    void testDeleteExam_WithNonExistingId() {
        Long id = 1L;
        when(examRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchExamException.class, () -> examService.deleteExam(id));
        verify(examRepository, never()).save(any());
    }

    @Test
    void testUpdateExam_WithExistingId() {
        Long id = 1L;
        ExamRequest request = mock(ExamRequest.class);
        Exam exam = new Exam();
        when(examRepository.findById(id)).thenReturn(Optional.of(exam));
        when(examRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        ExamDto updatedDto = examService.updateExam(request, id);
        assertNotNull(updatedDto);
        assertEquals(request.getTitle(), updatedDto.getTitle());
    }

    @Test
    void testUpdateExam_WithNonExistingId() {
        Long id = 1L;
        ExamRequest request = new ExamRequest();
        when(examRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchExamException.class, () -> examService.updateExam(request, id));
        verify(examRepository, never()).save(any());
    }

    @Test
    void testGetExam_WithExistingId() {
        Long id = 1L;
        Exam exam = mock(Exam.class);
        when(examRepository.findById(id)).thenReturn(Optional.of(exam));

        ExamDto examDto = examService.getExam(id);
        assertNotNull(examDto);
    }

    @Test
    void testGetExam_WithNonExistingId() {
        Long id = 1L;
        when(examRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchExamException.class, () -> examService.getExam(id));
    }
}

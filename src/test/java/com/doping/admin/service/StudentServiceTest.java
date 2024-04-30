package com.doping.admin.service;

import com.doping.admin.exception.NoSuchStudentException;
import com.doping.admin.persistence.entity.Student;
import com.doping.admin.persistence.model.dto.StudentDto;
import com.doping.admin.persistence.model.request.StudentCreateRequest;
import com.doping.admin.persistence.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void testGetStudent_WithExistingStudentNumber() {
        String studentNumber = "54354353";
        Student student = mock(Student.class);
        when(studentRepository.findByStudentNumber(studentNumber))
                .thenReturn(Optional.of(student));

        StudentDto studentDto = studentService.getStudent(studentNumber);
        assertNotNull(studentDto);
    }

    @Test
    void testGetStudent_WithNonExistingStudentNumber() {
        String studentNumber = "54354353";
        when(studentRepository.findByStudentNumber(studentNumber))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchStudentException.class, () -> studentService.getStudent(studentNumber));
    }

    @Test
    void testUpdateStudent_WithExistingStudentNumber() {
        String studentNumber = "54354353";
        StudentCreateRequest request = mock(StudentCreateRequest.class);
        Student student = mock(Student.class);
        when(studentRepository.findByStudentNumber(studentNumber))
                .thenReturn(Optional.of(student));
        when(studentRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        StudentDto updatedDto = studentService.updateStudent(request, studentNumber);
        assertNotNull(updatedDto);
        assertEquals(request.getEmail(), updatedDto.getEmail());
        assertEquals(request.getName(), updatedDto.getName());
        assertEquals(request.getSurname(), updatedDto.getSurname());
    }

    @Test
    void testUpdateStudent_WithNonExistingStudentNumber() {
        String studentNumber = "5435435";
        StudentCreateRequest request = mock(StudentCreateRequest.class);

        when(studentRepository.findByStudentNumber(studentNumber))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchStudentException.class, () -> studentService.updateStudent(request, studentNumber));
        verify(studentRepository, never()).save(any());
    }

    @Test
    void testDeleteStudent_WithNonExistingStudentNumber() {
        String studentNumber = "543543543";
        when(studentRepository.findByStudentNumber(studentNumber))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchStudentException.class, () -> studentService.deleteStudent(studentNumber));
        verify(studentRepository, never()).delete(any());
    }


    @Test
    void testDeleteStudent_WithExistingStudentNumber() {
        String studentNumber = "43543543";
        Student student = mock(Student.class);
        when(studentRepository.findByStudentNumber(studentNumber))
                .thenReturn(Optional.of(student));

        assertDoesNotThrow(() -> studentService.deleteStudent(studentNumber));
        verify(studentRepository, times(1)).delete(student);
    }
}

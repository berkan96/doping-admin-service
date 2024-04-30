package com.doping.admin.service;

import com.doping.admin.converter.StudentConverter;
import com.doping.admin.exception.NoSuchStudentException;
import com.doping.admin.persistence.entity.Student;
import com.doping.admin.persistence.model.dto.StudentDto;
import com.doping.admin.persistence.model.request.StudentCreateRequest;
import com.doping.admin.persistence.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public void createStudent(StudentCreateRequest request) {
        Student entity = StudentConverter.requestToEntity(request);
        entity.setStudentNumber(UUID.randomUUID().toString());
        studentRepository.save(entity);
    }

    public StudentDto getStudent(String studentNumber) {
        Student student = studentRepository.findByStudentNumber(studentNumber)
                .orElseThrow(NoSuchStudentException::new);
        return StudentConverter.entityToDto(student);
    }

    public StudentDto updateStudent(StudentCreateRequest request, String studentNumber) {
        Student student = studentRepository.findByStudentNumber(studentNumber)
                .orElseThrow(NoSuchStudentException::new);

        if (Objects.nonNull(request.getEmail())) {
            student.setEmail(request.getEmail());
        }
        if (Objects.nonNull(request.getName())) {
            student.setEmail(request.getName());
        }
        if (Objects.nonNull(request.getSurname())) {
            student.setEmail(request.getSurname());
        }
        student = studentRepository.save(student);
        return StudentConverter.entityToDto(student);
    }

    public void deleteStudent(String studentNumber) {
        Student student = studentRepository.findByStudentNumber(studentNumber)
                .orElseThrow(NoSuchStudentException::new);
        studentRepository.delete(student);
    }
}

package com.doping.admin.converter;

import com.doping.admin.persistence.entity.Student;
import com.doping.admin.persistence.model.dto.StudentDto;
import com.doping.admin.persistence.model.request.StudentCreateRequest;

public class StudentConverter {
    public static Student requestToEntity(StudentCreateRequest request) {
        Student student = new Student();
        student.setEmail(request.getEmail());
        student.setName(request.getName());
        student.setSurname(request.getSurname());
        return student;
    }

    public static StudentDto entityToDto(Student entity) {
        return StudentDto.builder()
                .name(entity.getName())
                .surname(entity.getSurname())
                .email(entity.getEmail())
                .build();
    }
}

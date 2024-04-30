package com.doping.admin.controller;


import com.doping.admin.persistence.model.dto.StudentDto;
import com.doping.admin.persistence.model.request.StudentCreateRequest;
import com.doping.admin.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    public final StudentService studentService;

    @PostMapping
    public void createStudent(@RequestBody @Valid StudentCreateRequest request) {
        studentService.createStudent(request);
    }

    @GetMapping("/{studentNumber}")
    public StudentDto getStudent(@PathVariable String studentNumber) {
        return studentService.getStudent(studentNumber);
    }

    @GetMapping("/delete/{studentNumber}")
    public void deleteStudent(@PathVariable String studentNumber) {
        studentService.deleteStudent(studentNumber);
    }

    @PatchMapping("/update/{studentNumber}")
    public StudentDto updateStudent(@PathVariable String studentNumber, @RequestBody StudentCreateRequest request) {
        return studentService.updateStudent(request, studentNumber);
    }
}

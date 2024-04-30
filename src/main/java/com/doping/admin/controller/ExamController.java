package com.doping.admin.controller;

import com.doping.admin.persistence.model.request.ExamRequest;
import com.doping.admin.persistence.model.dto.ExamDto;
import com.doping.admin.service.ExamService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @Operation(summary = "Save Exam")
    @PostMapping
    public ExamDto saveExam(@RequestBody @Valid ExamRequest request) {
        return examService.saveExam(request);
    }

    @Operation(summary = "Update The Exam According to the given exam id")
    @PatchMapping("/update/{id}")
    public ExamDto updateExam(@RequestBody @Valid ExamRequest request, @PathVariable Long id) {
        return examService.updateExam(request, id);
    }

    @Operation(summary = "Delete The Exam According to the given exam id")
    @DeleteMapping("/delete/{id}")
    public void deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
    }

    @Operation(summary = "Get The Exam According to the given exam id")
    @GetMapping("/{id}")
    public ExamDto getExam(@PathVariable Long id) {
        return examService.getExam(id);
    }
}

package com.doping.admin.controller;


import com.doping.admin.persistence.model.dto.ResultDto;
import com.doping.admin.persistence.model.dto.ReviewExamDto;
import com.doping.admin.persistence.model.request.SubmitExamRequest;
import com.doping.admin.service.ExamResultService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/result")
@RequiredArgsConstructor
public class ResultController {

    public final ExamResultService examResultService;

    @Operation(summary = "Submit Solved Exam.")
    @PostMapping
    public void submitExam(@RequestBody @Valid SubmitExamRequest request) {
        examResultService.submitExam(request);
    }

    @Operation(summary = "Get All Results Of Exams")
    @GetMapping("/all/{studentNumber}")
    public List<ResultDto> getAllResult(@PathVariable String studentNumber) {
        return examResultService.getAllExamResultByStudentNumber(studentNumber);
    }

    @Operation(summary = "Get Result Of Exam By Student Number And Exam ID")
    @GetMapping("/{studentNumber}")
    public ResultDto getExamResult(@PathVariable String studentNumber, @RequestParam(name = "examId") Long examId) {
        return examResultService.getExamResultByStudentNumberAndExamId(studentNumber, examId);
    }

    @Operation(summary = "Reviews The Exam Previously Solved")
    @GetMapping("/review/{studentNumber}")
    public ReviewExamDto reviewSolvedExam(@PathVariable String studentNumber, @RequestParam(name = "examId") Long examId) {
        return examResultService.reviewSolvedExam(studentNumber, examId);
    }
}

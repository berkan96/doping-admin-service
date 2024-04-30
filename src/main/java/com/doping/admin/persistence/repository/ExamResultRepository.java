package com.doping.admin.persistence.repository;

import com.doping.admin.persistence.entity.ExamResult;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ExamResultRepository extends CrudRepository<ExamResult, Long> {

    Optional<ExamResult> findByStudentNumberAndAndExamId(String studentNumber, Long examId);
    List<ExamResult> findAllByStudentNumber(String studentNumber);
}

package com.doping.admin.persistence.repository;

import com.doping.admin.persistence.entity.Exam;
import com.doping.admin.persistence.enums.ExamStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.ast.OpInc;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    Optional<Exam> findByIdAndStatusEquals(Long id, ExamStatus status);

    boolean existsByTitle(String title);
}

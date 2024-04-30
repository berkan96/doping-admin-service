package com.doping.admin.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "exam_result")
public class ExamResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String studentNumber;
    @OneToMany(mappedBy = "examResult", cascade = CascadeType.ALL)
    private List<Answer> answers;
    @Column
    private Integer correctAnswers;
    @Column
    private Integer wrongAnswers;
    @Column
    private Long examId;
    @Column
    private String examTitle;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}

package com.OnlineLearningPlatform.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_exam_results")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ExamResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    private Long studentId;

    private Double grade;


}
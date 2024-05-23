package com.OnlineLearningPlatform.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="t_registrations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    private Long studentId;
    private LocalDateTime dateTime;
}

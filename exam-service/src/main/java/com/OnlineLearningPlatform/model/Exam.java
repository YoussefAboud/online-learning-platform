package com.OnlineLearningPlatform.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name="t_exams")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int durationMinutes;

    private Long tcrId;

    @ElementCollection
    private List<LocalDate> availableDates;

    @ElementCollection
    private List<LocalDateTime> scheduledDates;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamResult> examResults;
}

package com.OnlineLearningPlatform.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_reviews")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private long studentId;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}

package com.OnlineLearningPlatform.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_courses_enrollment_mapping")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseEnrollMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long courseId;
    private long studentId;

}

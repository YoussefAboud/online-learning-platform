package com.OnlineLearningPlatform.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_enrollments_requests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollmentRequests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long instructorId;
    private long studentId;
    private long courseId;

}

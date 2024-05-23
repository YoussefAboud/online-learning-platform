package com.OnlineLearningPlatform.model;



import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_course_publication_request")
@Builder
public class CoursesPublicationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Long instructorId;

    private String name;
    private String category;
    private String duration;
    private String description;
    private int capacity;

    // 1 -> PENDING
    // 2 -> ACCEPTED
    // 3 -> REJECTED
    private int status;

}

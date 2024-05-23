package com.OnlineLearningPlatform.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoursePublicationRequestResponse {
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

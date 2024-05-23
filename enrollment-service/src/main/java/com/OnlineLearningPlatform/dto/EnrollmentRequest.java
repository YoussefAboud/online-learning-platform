package com.OnlineLearningPlatform.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollmentRequest {

    private long instructorId;
    private long studentId;
    private long courseId;

}

package com.OnlineLearningPlatform.dto;

import com.OnlineLearningPlatform.model.Reviews;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponse {
    private long id;
    private long instructorId;
    private String name;
    private String category;
    private String duration;
    private String description;
    private double rating;
    private  int capacity;
    private int number_of_enrolled_students;
    private List<Reviews> reviews_list;
}

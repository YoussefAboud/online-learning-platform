package com.OnlineLearningPlatform.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="t_courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long instructor_id;
    private String name;
    private String category;
    private String duration;
    private String description;
    private double rating;
    private int numberOfRates;
    private  int capacity;
    private int number_of_enrolled_students;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "course")
    private List<Reviews> reviews = new ArrayList<>();
}



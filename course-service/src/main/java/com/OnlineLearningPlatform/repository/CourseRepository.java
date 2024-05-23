package com.OnlineLearningPlatform.repository;

import com.OnlineLearningPlatform.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByName(String name);
    List<Course> findByCategory(String category);
    Course findByName(String name);
}

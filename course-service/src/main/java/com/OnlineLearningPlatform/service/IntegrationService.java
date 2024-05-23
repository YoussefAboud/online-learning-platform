package com.OnlineLearningPlatform.service;

import com.OnlineLearningPlatform.model.Course;
import com.OnlineLearningPlatform.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IntegrationService {
    private final CourseRepository courseRepository;
    public boolean checkCapacity(long courseId){

        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            return course.getNumber_of_enrolled_students() < course.getCapacity();
        }
        return false;
    }

    public Long getInstructor(Long courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            return course.getInstructor_id();
        }
        return null;
    }

    public void IncrementEnrollments(Long courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            course.setNumber_of_enrolled_students(course.getNumber_of_enrolled_students() + 1);
        }
    }

    public void decrementEnrollments(Long courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            course.setNumber_of_enrolled_students(course.getNumber_of_enrolled_students() + 1);
        }
    }
}

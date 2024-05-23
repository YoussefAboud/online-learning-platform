package com.OnlineLearningPlatform.repository;

import com.OnlineLearningPlatform.model.CourseEnrollMapping;
import com.OnlineLearningPlatform.model.EnrollmentRequests;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollMapping,Long> {
    List<CourseEnrollMapping> findByStudentId(Long studentId);

    Optional<CourseEnrollMapping> findByStudentIdAndCourseId(Long studentId, Long courseId);
}

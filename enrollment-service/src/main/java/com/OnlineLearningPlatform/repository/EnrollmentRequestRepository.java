package com.OnlineLearningPlatform.repository;

import com.OnlineLearningPlatform.model.EnrollmentRequests;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRequestRepository extends JpaRepository<EnrollmentRequests,Long> {
    List<EnrollmentRequests> findByStudentId(Long studentId);
}

package com.OnlineLearningPlatform.repository;

import com.OnlineLearningPlatform.model.CoursesPublicationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesPublicationRequestRepository extends JpaRepository<CoursesPublicationRequest,Long> {
    boolean existsByName(String name);
}

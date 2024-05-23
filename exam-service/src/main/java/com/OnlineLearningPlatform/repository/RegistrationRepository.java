package com.OnlineLearningPlatform.repository;

import com.OnlineLearningPlatform.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}

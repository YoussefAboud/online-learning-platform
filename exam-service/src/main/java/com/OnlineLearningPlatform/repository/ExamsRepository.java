package com.OnlineLearningPlatform.repository;

import com.OnlineLearningPlatform.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;

public interface ExamsRepository extends JpaRepository<Exam, Long> {
    Arrays findByTcrId(long tcrId);
}

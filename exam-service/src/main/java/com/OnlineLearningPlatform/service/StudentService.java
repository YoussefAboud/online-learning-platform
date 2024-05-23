package com.OnlineLearningPlatform.service;

import com.OnlineLearningPlatform.dto.ExamResultDto;
import com.OnlineLearningPlatform.model.Exam;
import com.OnlineLearningPlatform.model.ExamResult;
import com.OnlineLearningPlatform.model.Registration;
import com.OnlineLearningPlatform.repository.ExamResultRepository;
import com.OnlineLearningPlatform.repository.ExamsRepository;
import com.OnlineLearningPlatform.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final ExamsRepository examsRepository;
    private final ExamResultRepository examResultsRepository;
    private final RegistrationRepository registrationRepository;

    public void registerForExam(Long examId,Long studentId, LocalDateTime dateTime) {
        Optional<Exam> examOptional = examsRepository.findById(examId);

        if (examOptional.isEmpty()) {
            throw new IllegalArgumentException("Exam with ID " + examId + " not found.");
        }

        Exam exam = examOptional.get();

        if (!exam.getAvailableDates().contains(dateTime.toLocalDate())) {
            throw new IllegalArgumentException("The date " + dateTime.toLocalDate() + " is not available for this exam.");
        }

        if (!exam.getScheduledDates().contains(dateTime)) {
            throw new IllegalArgumentException("The date and time " + dateTime + " is not available for this exam.");
        }

        // Create and save a new Registration
        Registration registration = Registration.builder()
                .exam(exam)
                .studentId(studentId)
                .dateTime(dateTime)
                .build();

        registrationRepository.save(registration);
    }

    public List<ExamResultDto> getExamGrades(Long studentId) {
        List<ExamResult> examResults = examResultsRepository.findByStudentId(studentId);
        return examResults.stream()
                .map(this::mapExamResultToExamResultDto)
                .collect(Collectors.toList());
    }
    private ExamResultDto mapExamResultToExamResultDto(ExamResult examResult) {
        return ExamResultDto.builder()
                .studentId(examResult.getStudentId())
                .grade(examResult.getGrade())
                .examId(examResult.getExam().getId())
                .build();
    }
}

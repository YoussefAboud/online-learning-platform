package com.OnlineLearningPlatform.service;

import com.OnlineLearningPlatform.dto.ExamDto;
import com.OnlineLearningPlatform.dto.ExamRequest;
import com.OnlineLearningPlatform.dto.ExamResultDto;
import com.OnlineLearningPlatform.model.Exam;
import com.OnlineLearningPlatform.model.ExamResult;
import com.OnlineLearningPlatform.repository.ExamResultRepository;
import com.OnlineLearningPlatform.repository.ExamsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstructorService {
    private final ExamsRepository examsRepository;
    private final ExamResultRepository examResultsRepository;
    public ExamDto createExam(ExamRequest examRequest) {
        validateExamDates(examRequest.getAvailableDates(), examRequest.getScheduledDates());

        Exam exam = Exam.builder()
                .name(examRequest.getName())
                .durationMinutes(examRequest.getDurationMinutes())
                .tcrId(examRequest.getTCRId())
                .availableDates(examRequest.getAvailableDates())
                .scheduledDates(examRequest.getScheduledDates())
                .build();

        examsRepository.save(exam);
        return mapExamToExamDto(exam);

    }

    private void validateExamDates(List<LocalDate> availableDates, List<LocalDateTime> scheduledDates) {
        LocalDate today = LocalDate.now();

        // Ensure available dates are in the future
        for (LocalDate availableDate : availableDates) {
            if (!availableDate.isAfter(today)) {
                throw new IllegalArgumentException("Available dates must be in the future.");
            }
        }

        // Ensure no duplicate dates in available dates
        if (availableDates.stream().distinct().count() != availableDates.size()) {
            throw new IllegalArgumentException("Duplicate dates found in available dates.");
        }

        // Ensure scheduled dates are within available dates
        for (LocalDateTime scheduledDate : scheduledDates) {
            LocalDate scheduledLocalDate = scheduledDate.toLocalDate();
            if (!availableDates.contains(scheduledLocalDate)) {
                throw new IllegalArgumentException("Scheduled dates must be within available dates.");
            }
        }

        // Ensure no duplicate dates in scheduled dates
        if (scheduledDates.stream().distinct().count() != scheduledDates.size()) {
            throw new IllegalArgumentException("Duplicate dates found in scheduled dates.");
        }
    }


    public ExamResultDto setStudentGrade(Long studentId, Long examId, Double grade) {
        // Retrieve the exam by ID
        Optional<Exam> examOptional = examsRepository.findById(examId);
        if (examOptional.isEmpty()) {
            throw new IllegalArgumentException("Exam with ID " + examId + " not found.");
        }

        Exam exam = examOptional.get();

        // Create a new ExamResult
        ExamResult examResult = ExamResult.builder()
                .exam(exam)
                .studentId(studentId)
                .grade(grade)
                .build();

        // Save the new ExamResult
        examResultsRepository.save(examResult);

        // Return the saved ExamResult as a DTO
        return mapExamResultToExamResultDto(examResult);
    }


    public List<ExamDto> getAllExams(long tcrId) {

        List<Exam> allExams = examsRepository.findAll();

        List<Exam> ownedExams = allExams.stream()
                .filter(exam -> exam.getTcrId() == tcrId)
                .toList();

        return ownedExams.stream()
                .map(this::mapExamToExamDto)
                .collect(Collectors.toList());

    }
    private ExamDto mapExamToExamDto(Exam exam) {
        return ExamDto.builder()
                .id(exam.getId())
                .name(exam.getName())
                .durationMinutes(exam.getDurationMinutes())
                .TCRId(exam.getTcrId())
                .availableDates(exam.getAvailableDates())
                .scheduledDates(exam.getScheduledDates())
                .build();
    }

    private ExamResultDto mapExamResultToExamResultDto(ExamResult examResult) {
        return ExamResultDto.builder()
                .studentId(examResult.getStudentId())
                .grade(examResult.getGrade())
                .grade(examResult.getGrade())
                .build();
    }
}


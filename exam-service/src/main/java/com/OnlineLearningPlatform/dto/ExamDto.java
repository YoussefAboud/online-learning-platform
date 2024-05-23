package com.OnlineLearningPlatform.dto;

import com.OnlineLearningPlatform.model.ExamResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamDto {
    private long id;
    private String name;
    private Long TCRId;
    private int durationMinutes;
    private List<LocalDate> availableDates;
    private List<LocalDateTime> scheduledDates;
    private List<ExamResult> examResults;
}

package com.OnlineLearningPlatform.controller;

import com.OnlineLearningPlatform.dto.ExamDto;
import com.OnlineLearningPlatform.dto.ExamRequest;
import com.OnlineLearningPlatform.dto.ExamResultDto;
import com.OnlineLearningPlatform.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exam/instructor")
public class InstructorController {

    private final InstructorService instructorService;

    @PostMapping("/create")
    public ExamDto createExam(@RequestBody ExamRequest exam){
        return instructorService.createExam(exam);
    }

    @PostMapping("/grade")
    public ExamResultDto setStudentGrade(@RequestParam Long studentId, @RequestParam Long examId, @RequestParam Double grade){
        return instructorService.setStudentGrade(studentId,examId,grade);
    }

    @GetMapping("/getExams")
    public List<ExamDto> getAllExams(@RequestParam long tcrId) {
        return instructorService.getAllExams(tcrId);
    }
}

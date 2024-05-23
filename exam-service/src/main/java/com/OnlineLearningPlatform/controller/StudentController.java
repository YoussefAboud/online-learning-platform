package com.OnlineLearningPlatform.controller;

import com.OnlineLearningPlatform.dto.ExamResultDto;
import com.OnlineLearningPlatform.model.ExamResult;
import com.OnlineLearningPlatform.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exam/student")
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<Void> registerForExam(@RequestParam Long examId,@RequestParam Long studentId, @RequestParam LocalDateTime dateTime) {
        studentService.registerForExam(examId,studentId,dateTime);
        return ResponseEntity.ok().build();
    }

    

    @GetMapping("/grades")
    public List<ExamResultDto> getExamGrades(@RequestParam Long studentId) {
        return studentService.getExamGrades(studentId);
    }
}

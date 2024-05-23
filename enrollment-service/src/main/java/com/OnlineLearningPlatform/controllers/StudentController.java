package com.OnlineLearningPlatform.controllers;

import com.OnlineLearningPlatform.dto.EnrollmentResponse;
import com.OnlineLearningPlatform.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/enroll/student")
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/enroll")
    public EnrollmentResponse enroll(@RequestParam Long studentId, @RequestParam Long courseId) {
        return studentService.enroll(studentId, courseId);
    }

    @DeleteMapping("/cancel")
    public void cancelEnrollment(@RequestParam Long studentId,@RequestParam Long courseId) {
        studentService.cancelEnrollment(studentId,courseId);
    }

    @GetMapping("/getEnrollments")
    public List<EnrollmentResponse> getEnrollments(@RequestParam Long studentId) {
        return studentService.getEnrollments(studentId);
    }

}

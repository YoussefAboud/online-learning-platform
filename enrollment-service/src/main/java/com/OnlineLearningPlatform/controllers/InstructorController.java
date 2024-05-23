package com.OnlineLearningPlatform.controllers;

import com.OnlineLearningPlatform.dto.EnrollmentResponse;
import com.OnlineLearningPlatform.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/enroll/instructor")
public class InstructorController {
    private final InstructorService instructorService;

    @GetMapping("/getEnrollments")
    public List<EnrollmentResponse> getAllEnrollments(@RequestParam long instructorId) {
        return instructorService.getAllEnrollments(instructorId);
    }
    @PostMapping("/accept")
    public void acceptEnrollment(@RequestParam Long enrollmentId){
        instructorService.acceptEnrollRequest(enrollmentId);
    }

    @DeleteMapping("/reject")
    public void rejectEnrollment(@RequestParam Long enrollmentId){
        instructorService.rejectEnrollment(enrollmentId);
    }

}


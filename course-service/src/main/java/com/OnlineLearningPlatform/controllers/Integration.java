package com.OnlineLearningPlatform.controllers;

import com.OnlineLearningPlatform.service.IntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course/integration")
public class Integration {
    private final IntegrationService integrationService;

    @GetMapping("/capacity/{courseId}")
    public boolean checkCourseCapacity(@PathVariable Long courseId) {
        return integrationService.checkCapacity(courseId);
    }

    @GetMapping("/getInstructor/{courseId}")
    public Long getInstructor(@PathVariable Long courseId){
        return integrationService.getInstructor(courseId);
    }

    @PutMapping("/increment/{courseId}")
    public void increment(@PathVariable Long courseId){
        integrationService.IncrementEnrollments(courseId);
    }

    @PutMapping("/decrement/{courseId}")
    public void decrement(@PathVariable Long courseId){
        integrationService.decrementEnrollments(courseId);
    }

}

package com.OnlineLearningPlatform.controllers;

import com.OnlineLearningPlatform.dto.CoursePublicationRequestResponse;
import com.OnlineLearningPlatform.dto.CourseRequest;
import com.OnlineLearningPlatform.dto.CourseResponse;
import com.OnlineLearningPlatform.repository.CourseRepository;
import com.OnlineLearningPlatform.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course/admin")
public class AdminController {
    /*
     TODO
      1- review course content requests - view course information then accept or reject the publish request
      2- edit course information ( name - content - category - capacity )
      3- remove course
    */
    private final AdminService adminService;

    @GetMapping("/getAllCourses")
    public List<CourseResponse> getAllCourses() {
        return adminService.getAllCourses();
    }
    @GetMapping("/getPending")
    public List<CoursePublicationRequestResponse> getPendingRequests(){
        return adminService.getPendingRequests();
    }

    @PostMapping("/accept")
    public CourseResponse acceptPublishRequest(@RequestParam Long courseId){
        return adminService.acceptPublishRequest(courseId);
    }

    @DeleteMapping("/reject")
    public void rejectPublishRequest(@RequestParam Long courseId){
        adminService.rejectPublishRequest(courseId);
    }

    @DeleteMapping("/remove")
    public void removeCourse(@RequestParam Long courseId){
        adminService.removeCourse(courseId);
    }

}

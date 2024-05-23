package com.OnlineLearningPlatform.controllers;


import com.OnlineLearningPlatform.dto.CoursePublicationRequestResponse;
import com.OnlineLearningPlatform.dto.CourseResponse;
import com.OnlineLearningPlatform.dto.CourseRequest;
import com.OnlineLearningPlatform.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course/instructor")
public class InstructorController {
    /*
      TODO
       1- create course
       2- ( name - duration - category - capacity - description )
       3- view detailed information about each course
       4- search courses ( name - category )
       5- sort by rating
     */

    private final InstructorService instructorService;

    @PostMapping("/create")
    public CoursePublicationRequestResponse submitCourseRequest(@RequestBody CourseRequest courseRequest, @RequestParam Long instructorId){
        return instructorService.submitCourseRequest(courseRequest,instructorId);
    }

    @GetMapping("/getCourses")
    public List<CourseResponse> getCourses(@RequestParam Long instructorId){
        return  instructorService.getPublishedCourses(instructorId);
    }

    @GetMapping("/getAllCourses")
    public List<CourseResponse> getAllCourses() {
        return instructorService.getAllCourses();
    }

    @GetMapping("/search/name")
    public CourseResponse searchCourseByName(@RequestParam String name){
        return instructorService.searchCourseByName(name);
    }

    @GetMapping("/search/category")
    public List<CourseResponse> searchCourseByCategory(@RequestParam String category){
        return instructorService.searchCourseByCategory(category);
    }

    @GetMapping("/sort")
    public List<CourseResponse> sortCourse(){
        return instructorService.sortCourse();
    }
}


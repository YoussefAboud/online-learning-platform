package com.OnlineLearningPlatform.controllers;

import com.OnlineLearningPlatform.dto.CourseResponse;
import com.OnlineLearningPlatform.service.InstructorService;
import com.OnlineLearningPlatform.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course/student")
public class StudentController {
    /*
    TODO
     1- view detailed information about each course
     2- search courses ( name - category )
     3- sort by rating
     Integrating with Enrollment Service
       1- enroll in a course
       2- cancel course enrollment
       2- view current enrollments
       3- view past enrollments
    */

    private final StudentService studentService;

    @GetMapping("/getAllCourses")
    public List<CourseResponse> getAllCourses() {
        return studentService.getAllCourses();
    }

    @GetMapping("/search/name")
    public CourseResponse searchCourseByName(@RequestParam String name){
        return studentService.searchCourseByName(name);
    }

    @GetMapping("/search/category")
    public List<CourseResponse> searchCourseByCategory(@RequestParam String category){
        return studentService.searchCourseByCategory(category);
    }

    @GetMapping("/sort")
    public List<CourseResponse> sortCourse(){
        return studentService.sortCourse();
    }

    @PutMapping("/rate")
    public void rateCourse(@RequestParam long courseId,@RequestParam double rate){
        studentService.rateCourse(courseId,rate);
    }

    @PutMapping("/review")
    public void reviewCourse(@RequestParam long courseId,@RequestParam long studentId,@RequestParam String description){
        studentService.reviewCourse(courseId,studentId,description);
    }
}

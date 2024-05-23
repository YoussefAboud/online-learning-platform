package com.OnlineLearningPlatform.service;

import com.OnlineLearningPlatform.dto.CoursePublicationRequestResponse;
import com.OnlineLearningPlatform.dto.CourseRequest;
import com.OnlineLearningPlatform.dto.CourseResponse;
import com.OnlineLearningPlatform.model.Course;
import com.OnlineLearningPlatform.model.CoursesPublicationRequest;
import com.OnlineLearningPlatform.repository.CourseRepository;
import com.OnlineLearningPlatform.repository.CoursesPublicationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final CoursesPublicationRequestRepository requestRepository;
    private final CourseRepository courseRepository;

    public List<CoursePublicationRequestResponse> getPendingRequests() {
        List<CoursesPublicationRequest> courses = requestRepository.findAll();
        List<CoursePublicationRequestResponse> courseResponses = new ArrayList<>();
        for (CoursesPublicationRequest course : courses) {
            CoursePublicationRequestResponse response = mapCoursePublicationRequestToCourseResponse(course);
            courseResponses.add(response);
        }
        return courseResponses;
    }

    public CourseResponse acceptPublishRequest(Long courseId) {
        // Retrieve the course publication request from the repository
        Optional<CoursesPublicationRequest> requestOptional = requestRepository.findById(courseId);
        if (requestOptional.isEmpty()) {
            throw new RuntimeException("Course publication request not found for id: " + courseId);
        }

        CoursesPublicationRequest request = requestOptional.get();

        Course course = Course.builder()
                .instructor_id(request.getInstructorId())
                .name(request.getName())
                .category(request.getCategory())
                .duration(request.getDuration())
                .description(request.getDescription())
                .capacity(request.getCapacity())
                .rating(0.0)
                .numberOfRates(0)
                .number_of_enrolled_students(0)
                .build();

        // Save the new course to the database
        Course savedCourse = courseRepository.save(course);

        // Delete the course publication request from the repository
        requestRepository.delete(request);

        return mapCourseToCourseResponse(savedCourse);
    }

    public void rejectPublishRequest(Long courseId) {
        requestRepository.deleteById(courseId);
    }

    // Helper method to map Course entity to CourseResponse DTO
    private CourseResponse mapCourseToCourseResponse(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .category(course.getCategory())
                .duration(course.getDuration())
                .description(course.getDescription())
                .rating(course.getRating())
                .capacity(course.getCapacity())
                .number_of_enrolled_students(course.getNumber_of_enrolled_students())
                .reviews_list(course.getReviews())
                .build();
    }

    private CoursePublicationRequestResponse mapCoursePublicationRequestToCourseResponse(CoursesPublicationRequest course) {
        return CoursePublicationRequestResponse.builder()
                .id(course.getId())
                .instructorId(course.getInstructorId())
                .name(course.getName())
                .category(course.getCategory())
                .duration(course.getDuration())
                .description(course.getDescription())
                .capacity(course.getCapacity())
                .status(course.getStatus())
                .build();
    }

    public void removeCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    public List<CourseResponse> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponse> courseResponses = new ArrayList<>();
        for (Course course : courses) {
            CourseResponse response = mapCourseToCourseResponse(course);
            courseResponses.add(response);
        }
        return courseResponses;
    }
}

package com.OnlineLearningPlatform.service;

import com.OnlineLearningPlatform.dto.CoursePublicationRequestResponse;
import com.OnlineLearningPlatform.dto.CourseResponse;
import com.OnlineLearningPlatform.dto.CourseRequest;
import com.OnlineLearningPlatform.model.Course;
import com.OnlineLearningPlatform.model.CoursesPublicationRequest;
import com.OnlineLearningPlatform.repository.CourseRepository;
import com.OnlineLearningPlatform.repository.CoursesPublicationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final CourseRepository courseRepository;
    private final CoursesPublicationRequestRepository requestRepository;

    // create course
    public CoursePublicationRequestResponse submitCourseRequest(CourseRequest courseRequest,long instructorID) {
        // Check if the course with the same name already exists
        if (requestRepository.existsByName(courseRequest.getName())) {
            throw new RuntimeException("Course with name '" + courseRequest.getName() + "' already requested");
        }

        // Map CourseRequest to CourserPublicationRequest entity
        CoursesPublicationRequest request = CoursesPublicationRequest.builder()
                .instructorId(instructorID)
                .name(courseRequest.getName())
                .category(courseRequest.getCategory())
                .duration(courseRequest.getDuration())
                .description(courseRequest.getDescription())
                .capacity(courseRequest.getCapacity())
                .status(1)
                .build();

        // Save the new request to the database
        CoursesPublicationRequest savedCourse = requestRepository.save(request);

        // Map the saved course back to CourseResponse
        return mapCourseRequestToCoursePublicationResponse(savedCourse);
    }



    // search course by name
    public CourseResponse searchCourseByName(String name) {
        // Find course by name from repository
        Course course = courseRepository.findByName(name);

        // If course is found, map it to CourseResponse
        if (course != null) {
            return mapCourseToCourseResponse(course);
        } else {
            return null; // Or throw an exception if course not found
        }
    }

    // search course by category
    public List<CourseResponse> searchCourseByCategory(String category) {
        // Find courses by category from repository
        List<Course> courses = courseRepository.findByCategory(category);

        // Map list of courses to list of CourseResponse
        return courses.stream()
                .map(this::mapCourseToCourseResponse)
                .collect(Collectors.toList());
    }

    public List<CourseResponse> sortCourse() {
        // Fetch all courses from repository and sort by rating
        List<Course> courses = courseRepository.findAll(Sort.by(Sort.Direction.DESC, "rating"));

        // Map list of sorted courses to list of CourseResponse
        return courses.stream()
                .map(this::mapCourseToCourseResponse)
                .collect(Collectors.toList());
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

    // Helper method to map Course entity to CourseResponse DTO
    private CoursePublicationRequestResponse mapCourseRequestToCoursePublicationResponse(CoursesPublicationRequest course) {
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

    public List<CourseResponse> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponse> courseResponses = new ArrayList<>();
        for (Course course : courses) {
            CourseResponse response = mapCourseToCourseResponse(course);
            courseResponses.add(response);
        }
        return courseResponses;
    }

    public List<CourseResponse> getPublishedCourses(Long instructorId) {
        List<Course> allCourses = courseRepository.findAll();

        List<Course> ownedCourses = allCourses.stream()
                .filter(course -> course.getInstructor_id() == instructorId)
                .toList();

        return ownedCourses.stream()
                .map(this::mapCourseToCourseResponse)
                .collect(Collectors.toList());
    }
}

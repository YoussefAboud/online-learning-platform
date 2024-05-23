package com.OnlineLearningPlatform.service;

import com.OnlineLearningPlatform.dto.CourseResponse;
import com.OnlineLearningPlatform.model.Course;
import com.OnlineLearningPlatform.model.Reviews;
import com.OnlineLearningPlatform.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class StudentService {
    private final CourseRepository courseRepository;
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

    public List<CourseResponse> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponse> courseResponses = new ArrayList<>();
        for (Course course : courses) {
            CourseResponse response = mapCourseToCourseResponse(course);
            courseResponses.add(response);
        }
        return courseResponses;
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

    public void rateCourse(long courseId,double rate) {
        Course course = courseRepository.findById(courseId).get();
        double totalRating = course.getRating() + rate;
        int numberOfRatings = course.getNumberOfRates() + 1;
        double accumulatedRating = totalRating / numberOfRatings;

        course.setNumberOfRates(numberOfRatings);
        course.setRating(accumulatedRating);

        courseRepository.save(course);
    }

    public void reviewCourse(long courseId, long studentId, String description) {
        Course course  = courseRepository.findById(courseId).get();

        Reviews review = Reviews.builder()
                .course(course)
                .studentId(studentId)
                .description(description)
                .build();

        course.getReviews().add(review);
        courseRepository.save(course);
    }
}

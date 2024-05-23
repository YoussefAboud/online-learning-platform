package com.OnlineLearningPlatform.service;

import com.OnlineLearningPlatform.dto.EnrollmentResponse;
import com.OnlineLearningPlatform.model.CourseEnrollMapping;
import com.OnlineLearningPlatform.model.EnrollmentRequests;
import com.OnlineLearningPlatform.repository.CourseEnrollmentRepository;
import com.OnlineLearningPlatform.repository.EnrollmentRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final CourseEnrollmentRepository enrollmentRepository;
    private final EnrollmentRequestRepository requestRepository;


    public EnrollmentResponse enroll(long studentId, long courseId){


        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/api/course/integration/capacity/"+courseId);
        ResponseEntity<Boolean> response = restTemplate.getForEntity(builder.toUriString(), Boolean.class);

        if (Boolean.TRUE.equals(response.getBody())) {

            builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/api/course/integration/getInstructor/"+courseId);
            ResponseEntity<Long> response1 = restTemplate.getForEntity(builder.toUriString(), Long.class);

            long instructorId = response1.getBody();

            EnrollmentRequests enrollmentRequest = EnrollmentRequests.builder()
                    .instructorId(instructorId)
                    .studentId(studentId)
                    .courseId(courseId)
                    .build();

            requestRepository.save(enrollmentRequest);
            return mapRequestToResponse(enrollmentRequest);
        } else {
            throw new RuntimeException("Course enrollment failed: no capacity available");
        }
    }

    public void cancelEnrollment(Long studentId, Long courseId) {
        Optional<CourseEnrollMapping> enrollmentMapping = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put("http://localhost:8081/api/course/integration/decrement/"+courseId, null);
        enrollmentMapping.ifPresent(enrollmentRepository::delete);
    }

    private EnrollmentResponse mapRequestToResponse(EnrollmentRequests enrollmentRequest){
        return EnrollmentResponse.builder()
                .studentId(enrollmentRequest.getStudentId())
                .instructorId(enrollmentRequest.getInstructorId())
                .courseId(enrollmentRequest.getCourseId())
                .build();
    }



    public List<EnrollmentResponse> getEnrollments(Long studentId) {
        List<CourseEnrollMapping> enrollmentMappings = enrollmentRepository.findByStudentId(studentId);
        return enrollmentMappings.stream()
                .map(this::mapMappingToResponse)
                .collect(Collectors.toList());
    }

    private EnrollmentResponse mapMappingToResponse(CourseEnrollMapping enrollmentMapping) {
        return EnrollmentResponse.builder()
                .enrollmentId(enrollmentMapping.getId())
                .studentId(enrollmentMapping.getStudentId())
                .courseId(enrollmentMapping.getCourseId())
                .build();
    }

}

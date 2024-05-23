package com.OnlineLearningPlatform.service;

import com.OnlineLearningPlatform.dto.EnrollmentResponse;
import com.OnlineLearningPlatform.event.EnrollmentEvent;
import com.OnlineLearningPlatform.model.CourseEnrollMapping;
import com.OnlineLearningPlatform.model.EnrollmentRequests;
import com.OnlineLearningPlatform.repository.CourseEnrollmentRepository;
import com.OnlineLearningPlatform.repository.EnrollmentRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstructorService {
    private final CourseEnrollmentRepository enrollmentRepository;
    private final EnrollmentRequestRepository requestRepository;
    private final KafkaTemplate<String, EnrollmentEvent> kafkaTemplate;
    public void acceptEnrollRequest(long enrollmentId) {
        Optional<EnrollmentRequests> request = requestRepository.findById(enrollmentId);
        if (request.isPresent()) {

            enrollmentRepository.save(CourseEnrollMapping.builder()
                    .studentId(request.get().getStudentId())
                    .courseId(request.get().getCourseId())
                    .build()
            );
            kafkaTemplate.send("notificationTopic", new EnrollmentEvent(request.get().getStudentId()));
            long courseId = request.get().getCourseId();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.put("http://localhost:8081/api/course/integration/increment/"+courseId, null);
            requestRepository.deleteById(enrollmentId);
        }
    }

    public List<EnrollmentResponse> getAllEnrollments(long instructorId) {
        // Find all enrollment requests from the repository
        List<EnrollmentRequests> allRequests = requestRepository.findAll();

        // Filter requests to include only those with matching instructorId
        List<EnrollmentRequests> matchingRequests = allRequests.stream()
                .filter(request -> request.getInstructorId() == instructorId)
                .toList();

        // Map filtered requests to EnrollmentResponse objects

        return matchingRequests.stream()
                .map(this::mapEnrollmentRequestToResponse)
                .collect(Collectors.toList());
    }

    private EnrollmentResponse mapEnrollmentRequestToResponse(EnrollmentRequests enrollmentRequest) {
        return EnrollmentResponse.builder()
                .enrollmentId(enrollmentRequest.getId())
                .studentId(enrollmentRequest.getStudentId())
                .instructorId(enrollmentRequest.getInstructorId())
                .courseId(enrollmentRequest.getCourseId())
                .build();
    }

    public void rejectEnrollment(Long enrollmentId) {
        requestRepository.deleteById(enrollmentId);
    }
}

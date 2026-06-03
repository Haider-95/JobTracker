package com.JobTracker.application_service.service;

import com.JobTracker.application_service.dto.JobApplicationRequest;
import com.JobTracker.application_service.dto.JobApplicationResponse;
import com.JobTracker.application_service.model.JobApplication;
import com.JobTracker.application_service.repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository repository;

    public List<JobApplicationResponse> getAllByUser(String userEmail) {
        return repository.findByUserEmail(userEmail)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public JobApplicationResponse create(JobApplicationRequest request, String userEmail) {
        JobApplication app = new JobApplication();
        app.setCompany(request.getCompany());
        app.setRole(request.getRole());
        app.setStatus(request.getStatus() != null ? request.getStatus() : "APPLIED");
        app.setNotes(request.getNotes());
        app.setUserEmail(userEmail);
        app.setAppliedDate(LocalDateTime.now());
        return toResponse(repository.save(app));
    }

    public JobApplicationResponse update(Long id, JobApplicationRequest request, String userEmail) {
        JobApplication app = repository.findByIdAndUserEmail(id, userEmail)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        app.setCompany(request.getCompany());
        app.setRole(request.getRole());
        app.setStatus(request.getStatus());
        app.setNotes(request.getNotes());
        return toResponse(repository.save(app));
    }

    public void delete(Long id, String userEmail) {
        JobApplication app = repository.findByIdAndUserEmail(id, userEmail)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        repository.delete(app);
    }

    private JobApplicationResponse toResponse(JobApplication app) {
        JobApplicationResponse response = new JobApplicationResponse();
        response.setId(app.getId());
        response.setCompany(app.getCompany());
        response.setRole(app.getRole());
        response.setStatus(app.getStatus());
        response.setNotes(app.getNotes());
        response.setUserEmail(app.getUserEmail());
        response.setAppliedDate(app.getAppliedDate());
        return response;
    }
}
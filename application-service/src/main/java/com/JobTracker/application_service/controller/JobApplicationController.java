package com.JobTracker.application_service.controller;

import com.JobTracker.application_service.dto.JobApplicationRequest;
import com.JobTracker.application_service.dto.JobApplicationResponse;
import com.JobTracker.application_service.service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @GetMapping
    public ResponseEntity<List<JobApplicationResponse>> getAll(Authentication auth) {
        return ResponseEntity.ok(jobApplicationService.getAllByUser(auth.getName()));
    }

    @PostMapping
    public ResponseEntity<JobApplicationResponse> create(
            @Valid @RequestBody JobApplicationRequest request,
            Authentication auth) {
        return ResponseEntity.ok(jobApplicationService.create(request, auth.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobApplicationResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody JobApplicationRequest request,
            Authentication auth) {
        return ResponseEntity.ok(jobApplicationService.update(id, request, auth.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, Authentication auth) {
        jobApplicationService.delete(id, auth.getName());
        return ResponseEntity.noContent().build();
    }
}
package com.JobTracker.application_service.repository;

import com.JobTracker.application_service.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByUserEmail(String userEmail);
    Optional<JobApplication> findByIdAndUserEmail(Long id, String userEmail);
}
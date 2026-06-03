package com.JobTracker.application_service.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class JobApplicationResponse {
    private Long id;
    private String company;
    private String role;
    private String status;
    private String notes;
    private String userEmail;
    private LocalDateTime appliedDate;
}
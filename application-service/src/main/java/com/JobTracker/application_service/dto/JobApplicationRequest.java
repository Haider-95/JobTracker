package com.JobTracker.application_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JobApplicationRequest {

    @NotBlank(message = "Company is required")
    private String company;

    @NotBlank(message = "Role is required")
    private String role;

    private String status;
    private String notes;
}
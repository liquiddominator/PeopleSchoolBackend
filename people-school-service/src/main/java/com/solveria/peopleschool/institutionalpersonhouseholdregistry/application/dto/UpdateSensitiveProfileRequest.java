package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateSensitiveProfileRequest(
        String bloodTypeCode,
        String emergencyMedicalNotes,
        @NotBlank String sensitiveProfileStatus,
        String lastReviewedAt,
        String lastReviewedBy) {}

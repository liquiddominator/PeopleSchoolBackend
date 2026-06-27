package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateSensitiveProfileRequest(
        @NotNull Long personId,
        String bloodTypeCode,
        String emergencyMedicalNotes,
        @NotBlank String sensitiveProfileStatus,
        String lastReviewedAt,
        String lastReviewedBy) {}

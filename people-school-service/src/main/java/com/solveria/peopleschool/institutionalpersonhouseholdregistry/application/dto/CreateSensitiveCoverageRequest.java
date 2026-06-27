package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateSensitiveCoverageRequest(
        @NotNull Long personSensitiveProfileId,
        @NotNull Long personId,
        @NotBlank String coverageTypeCode,
        String providerName,
        String policyNumber,
        @NotBlank String coverageStatus,
        String effectiveFrom,
        String effectiveTo,
        String notes) {}

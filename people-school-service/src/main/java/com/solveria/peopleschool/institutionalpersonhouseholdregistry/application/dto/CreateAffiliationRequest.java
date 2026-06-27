package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAffiliationRequest(
        @NotNull Long personId,
        @NotBlank String affiliationTypeCode,
        Long siteId,
        Long organizationalUnitId,
        @NotBlank String affiliationStatus,
        String effectiveFrom,
        String effectiveTo) {}

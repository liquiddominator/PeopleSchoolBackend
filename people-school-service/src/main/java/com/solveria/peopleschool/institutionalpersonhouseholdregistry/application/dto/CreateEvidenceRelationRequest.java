package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateEvidenceRelationRequest(
        Long personId,
        Long householdId,
        @NotBlank String relatedContextType,
        @NotBlank String relatedContextRefId,
        @NotBlank String evidenceRoleCode,
        @NotNull Long assetId,
        @NotBlank String evidenceStatus) {}

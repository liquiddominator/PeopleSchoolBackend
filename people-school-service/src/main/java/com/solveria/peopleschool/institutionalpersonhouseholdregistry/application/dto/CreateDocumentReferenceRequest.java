package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateDocumentReferenceRequest(
        @NotNull Long personId,
        @NotBlank String documentReferenceTypeCode,
        @NotNull Long assetId,
        @NotBlank String referenceStatus,
        String notes) {}

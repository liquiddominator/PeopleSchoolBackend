package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateIdentityLinkRequest(
        @NotNull Long personId,
        @NotBlank String iamSubjectId,
        String identityProviderCode,
        @NotBlank String identityLinkStatus,
        @NotBlank String linkedAt,
        String linkedBy) {}

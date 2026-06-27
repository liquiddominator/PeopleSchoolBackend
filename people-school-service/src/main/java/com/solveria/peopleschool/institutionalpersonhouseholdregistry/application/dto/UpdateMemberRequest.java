package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateMemberRequest(
        @NotBlank String membershipRoleCode, @NotNull Boolean isPrimaryGuardianGroup) {}

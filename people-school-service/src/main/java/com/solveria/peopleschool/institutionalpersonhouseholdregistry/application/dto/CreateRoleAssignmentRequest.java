package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalroleassignment.RoleAssignmentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record CreateRoleAssignmentRequest(
        @NotNull Long personId,
        @NotBlank @Size(max = 50) String roleCode,
        @NotNull RoleAssignmentStatus roleStatus,
        LocalDate effectiveFrom,
        LocalDate effectiveTo) {}

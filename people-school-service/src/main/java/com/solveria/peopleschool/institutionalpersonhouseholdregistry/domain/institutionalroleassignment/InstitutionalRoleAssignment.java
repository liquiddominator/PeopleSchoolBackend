package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalroleassignment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record InstitutionalRoleAssignment(
        Long id,
        Long personId,
        String roleCode,
        RoleAssignmentStatus roleStatus,
        LocalDate effectiveFrom,
        LocalDate effectiveTo,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

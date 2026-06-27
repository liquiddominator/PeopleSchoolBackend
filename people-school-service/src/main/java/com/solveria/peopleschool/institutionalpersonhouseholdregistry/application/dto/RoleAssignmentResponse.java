package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalroleassignment.InstitutionalRoleAssignment;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalroleassignment.RoleAssignmentStatus;
import java.time.LocalDate;

public record RoleAssignmentResponse(
        Long id,
        Long personId,
        String roleCode,
        RoleAssignmentStatus roleStatus,
        LocalDate effectiveFrom,
        LocalDate effectiveTo) {

    public static RoleAssignmentResponse from(InstitutionalRoleAssignment a) {
        return new RoleAssignmentResponse(
                a.id(),
                a.personId(),
                a.roleCode(),
                a.roleStatus(),
                a.effectiveFrom(),
                a.effectiveTo());
    }
}

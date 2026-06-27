package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.guardianrelationship.GuardianAuthorityStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.guardianrelationship.GuardianTypeCode;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreateGuardianRelationshipRequest(
        @NotNull Long studentPersonId,
        @NotNull Long guardianPersonId,
        Long householdId,
        @NotNull GuardianTypeCode guardianTypeCode,
        @NotNull GuardianAuthorityStatus legalAuthorityStatus,
        @NotNull GuardianAuthorityStatus schoolAuthorityStatus,
        LocalDate effectiveFrom,
        LocalDate effectiveTo,
        String notes) {}

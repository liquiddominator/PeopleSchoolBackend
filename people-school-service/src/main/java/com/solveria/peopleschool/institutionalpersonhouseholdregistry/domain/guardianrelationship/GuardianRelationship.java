package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.guardianrelationship;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record GuardianRelationship(
        Long id,
        Long studentPersonId,
        Long guardianPersonId,
        Long householdId,
        GuardianTypeCode guardianTypeCode,
        GuardianAuthorityStatus legalAuthorityStatus,
        GuardianAuthorityStatus schoolAuthorityStatus,
        LocalDate effectiveFrom,
        LocalDate effectiveTo,
        String notes,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

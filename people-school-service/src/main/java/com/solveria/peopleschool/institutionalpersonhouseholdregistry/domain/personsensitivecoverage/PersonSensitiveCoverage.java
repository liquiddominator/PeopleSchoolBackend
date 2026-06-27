package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitivecoverage;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PersonSensitiveCoverage(
        Long id,
        Long personSensitiveProfileId,
        Long personId,
        String coverageTypeCode,
        String providerName,
        String policyNumber,
        CoverageStatus coverageStatus,
        LocalDate effectiveFrom,
        LocalDate effectiveTo,
        String notes,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

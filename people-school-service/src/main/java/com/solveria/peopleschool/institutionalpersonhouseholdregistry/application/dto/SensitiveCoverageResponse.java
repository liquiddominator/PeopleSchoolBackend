package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitivecoverage.PersonSensitiveCoverage;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record SensitiveCoverageResponse(
        Long id,
        Long personSensitiveProfileId,
        Long personId,
        String coverageTypeCode,
        String providerName,
        String policyNumber,
        String coverageStatus,
        LocalDate effectiveFrom,
        LocalDate effectiveTo,
        String notes,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {

    public static SensitiveCoverageResponse from(PersonSensitiveCoverage c) {
        return new SensitiveCoverageResponse(
                c.id(),
                c.personSensitiveProfileId(),
                c.personId(),
                c.coverageTypeCode(),
                c.providerName(),
                c.policyNumber(),
                c.coverageStatus().name(),
                c.effectiveFrom(),
                c.effectiveTo(),
                c.notes(),
                c.tenantId(),
                c.createdAt(),
                c.createdBy(),
                c.lastModifiedAt(),
                c.lastModifiedBy(),
                c.version());
    }
}

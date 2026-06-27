package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalaffiliation;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record InstitutionalAffiliation(
        Long id,
        Long personId,
        String affiliationTypeCode,
        Long siteId,
        Long organizationalUnitId,
        AffiliationStatus affiliationStatus,
        LocalDate effectiveFrom,
        LocalDate effectiveTo,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

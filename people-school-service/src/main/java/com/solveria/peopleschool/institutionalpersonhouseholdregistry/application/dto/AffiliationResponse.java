package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalaffiliation.InstitutionalAffiliation;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record AffiliationResponse(
        Long id,
        Long personId,
        String affiliationTypeCode,
        Long siteId,
        Long organizationalUnitId,
        String affiliationStatus,
        LocalDate effectiveFrom,
        LocalDate effectiveTo,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {

    public static AffiliationResponse from(InstitutionalAffiliation a) {
        return new AffiliationResponse(
                a.id(),
                a.personId(),
                a.affiliationTypeCode(),
                a.siteId(),
                a.organizationalUnitId(),
                a.affiliationStatus().name(),
                a.effectiveFrom(),
                a.effectiveTo(),
                a.tenantId(),
                a.createdAt(),
                a.createdBy(),
                a.lastModifiedAt(),
                a.lastModifiedBy(),
                a.version());
    }
}

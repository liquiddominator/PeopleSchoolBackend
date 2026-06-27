package com.solveria.peopleschool.governanceregistry.domain.unitsite;

import java.time.LocalDateTime;

public record UnitSite(
        Long id,
        Long educationUnitId,
        String siteCode,
        String siteName,
        String addressLine,
        String cityName,
        String departmentName,
        String phoneNumber,
        String emailAddress,
        boolean isMainSite,
        SiteStatus siteStatus,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

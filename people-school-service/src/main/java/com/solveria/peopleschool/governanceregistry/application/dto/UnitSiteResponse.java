package com.solveria.peopleschool.governanceregistry.application.dto;

import com.solveria.peopleschool.governanceregistry.domain.unitsite.SiteStatus;
import com.solveria.peopleschool.governanceregistry.domain.unitsite.UnitSite;
import java.time.LocalDateTime;

public record UnitSiteResponse(
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
        LocalDateTime lastModifiedAt) {

    public static UnitSiteResponse from(UnitSite s) {
        return new UnitSiteResponse(
                s.id(),
                s.educationUnitId(),
                s.siteCode(),
                s.siteName(),
                s.addressLine(),
                s.cityName(),
                s.departmentName(),
                s.phoneNumber(),
                s.emailAddress(),
                s.isMainSite(),
                s.siteStatus(),
                s.tenantId(),
                s.createdAt(),
                s.lastModifiedAt());
    }
}

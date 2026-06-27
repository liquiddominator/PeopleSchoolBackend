package com.solveria.peopleschool.governanceregistry.application.dto;

import com.solveria.peopleschool.governanceregistry.domain.unitsite.SiteStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUnitSiteRequest(
        Long educationUnitId,
        @NotBlank @Size(max = 50) String siteCode,
        @NotBlank @Size(max = 180) String siteName,
        @Size(max = 250) String addressLine,
        @Size(max = 120) String cityName,
        @Size(max = 120) String departmentName,
        @Size(max = 50) String phoneNumber,
        @Size(max = 150) String emailAddress,
        boolean isMainSite,
        @NotNull SiteStatus siteStatus) {}

package com.solveria.peopleschool.governanceregistry.application.dto;

import com.solveria.peopleschool.governanceregistry.domain.orggroup.OrgGroupStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateOrgGroupRequest(
        @NotBlank @Size(max = 40) String tenantCode,
        @NotBlank @Size(max = 220) String legalName,
        @Size(max = 220) String commercialName,
        @Size(max = 60) String taxIdentifier,
        @NotBlank @Size(max = 10) String countryCode,
        @NotBlank @Size(max = 10) String defaultCurrencyCode,
        @NotBlank @Size(max = 80) String defaultTimezone,
        @NotNull OrgGroupStatus orgGroupStatus) {}

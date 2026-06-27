package com.solveria.peopleschool.governanceregistry.application.dto;

import com.solveria.peopleschool.governanceregistry.domain.orgunit.OrgUnitScopeStatus;
import com.solveria.peopleschool.governanceregistry.domain.orgunit.OrgUnitType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateOrganizationalUnitRequest(
        @NotBlank @Size(max = 180) String orgUnitName,
        @NotNull OrgUnitType orgUnitType,
        Long parentOrgUnitId,
        @Min(0) @Max(999) int displayOrder,
        @NotNull OrgUnitScopeStatus unitScopeStatus) {}

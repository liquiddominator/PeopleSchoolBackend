package com.solveria.peopleschool.governanceregistry.domain.orgunit;

import java.time.LocalDateTime;

public record OrganizationalUnit(
        Long id,
        Long educationUnitId,
        Long parentOrgUnitId,
        String orgUnitCode,
        String orgUnitName,
        OrgUnitType orgUnitType,
        int displayOrder,
        OrgUnitScopeStatus unitScopeStatus,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

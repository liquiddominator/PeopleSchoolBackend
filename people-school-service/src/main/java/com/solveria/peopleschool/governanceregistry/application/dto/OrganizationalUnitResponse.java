package com.solveria.peopleschool.governanceregistry.application.dto;

import com.solveria.peopleschool.governanceregistry.domain.orgunit.OrgUnitScopeStatus;
import com.solveria.peopleschool.governanceregistry.domain.orgunit.OrgUnitType;
import com.solveria.peopleschool.governanceregistry.domain.orgunit.OrganizationalUnit;
import java.time.LocalDateTime;

public record OrganizationalUnitResponse(
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
        LocalDateTime lastModifiedAt) {

    public static OrganizationalUnitResponse from(OrganizationalUnit u) {
        return new OrganizationalUnitResponse(
                u.id(),
                u.educationUnitId(),
                u.parentOrgUnitId(),
                u.orgUnitCode(),
                u.orgUnitName(),
                u.orgUnitType(),
                u.displayOrder(),
                u.unitScopeStatus(),
                u.tenantId(),
                u.createdAt(),
                u.lastModifiedAt());
    }
}

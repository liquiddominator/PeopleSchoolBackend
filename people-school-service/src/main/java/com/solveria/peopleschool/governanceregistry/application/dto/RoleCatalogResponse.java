package com.solveria.peopleschool.governanceregistry.application.dto;

import com.solveria.peopleschool.governanceregistry.domain.rolecatalog.InstitutionalRoleCatalog;
import com.solveria.peopleschool.governanceregistry.domain.rolecatalog.RoleCatalogStatus;
import com.solveria.peopleschool.governanceregistry.domain.rolecatalog.RoleFamily;
import java.time.LocalDateTime;

public record RoleCatalogResponse(
        Long id,
        Long educationUnitId,
        String roleCode,
        String roleName,
        RoleFamily roleFamily,
        boolean isAssignableToPerson,
        RoleCatalogStatus roleCatalogStatus,
        int displayOrder,
        String tenantId,
        LocalDateTime createdAt,
        LocalDateTime lastModifiedAt) {

    public static RoleCatalogResponse from(InstitutionalRoleCatalog r) {
        return new RoleCatalogResponse(
                r.id(),
                r.educationUnitId(),
                r.roleCode(),
                r.roleName(),
                r.roleFamily(),
                r.isAssignableToPerson(),
                r.roleCatalogStatus(),
                r.displayOrder(),
                r.tenantId(),
                r.createdAt(),
                r.lastModifiedAt());
    }
}

package com.solveria.peopleschool.governanceregistry.domain.rolecatalog;

import java.time.LocalDateTime;

public record InstitutionalRoleCatalog(
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
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

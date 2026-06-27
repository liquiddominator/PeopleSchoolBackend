package com.solveria.peopleschool.governanceregistry.application.dto;

import com.solveria.peopleschool.governanceregistry.domain.rolecatalog.RoleCatalogStatus;
import com.solveria.peopleschool.governanceregistry.domain.rolecatalog.RoleFamily;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateRoleCatalogRequest(
        @NotBlank @Size(max = 60) String roleCode,
        @NotBlank @Size(max = 180) String roleName,
        @NotNull RoleFamily roleFamily,
        boolean isAssignableToPerson,
        @NotNull RoleCatalogStatus roleCatalogStatus,
        @Min(0) @Max(999) int displayOrder) {}

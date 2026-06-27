package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.governanceregistry.application.dto.UpdateRoleCatalogRequest;
import com.solveria.peopleschool.governanceregistry.domain.rolecatalog.InstitutionalRoleCatalog;
import com.solveria.peopleschool.governanceregistry.domain.rolecatalog.RoleCatalogRepositoryPort;

public class UpdateRoleCatalogUseCase {

    private final RoleCatalogRepositoryPort repository;

    public UpdateRoleCatalogUseCase(RoleCatalogRepositoryPort repository) {
        this.repository = repository;
    }

    public InstitutionalRoleCatalog execute(
            Long id, UpdateRoleCatalogRequest request, String tenantId) {
        InstitutionalRoleCatalog existing =
                repository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new EntityNotFoundException(
                                                "InstitutionalRoleCatalog", id.toString()));
        InstitutionalRoleCatalog updated =
                new InstitutionalRoleCatalog(
                        existing.id(),
                        existing.educationUnitId(),
                        existing.roleCode(),
                        request.roleName(),
                        request.roleFamily(),
                        request.isAssignableToPerson(),
                        request.roleCatalogStatus(),
                        request.displayOrder(),
                        tenantId,
                        existing.createdAt(),
                        existing.createdBy(),
                        null,
                        null,
                        existing.version());
        return repository.save(updated);
    }
}

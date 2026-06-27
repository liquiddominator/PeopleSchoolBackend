package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.governanceregistry.domain.rolecatalog.RoleCatalogRepositoryPort;

public class DeleteRoleCatalogUseCase {

    private final RoleCatalogRepositoryPort repository;

    public DeleteRoleCatalogUseCase(RoleCatalogRepositoryPort repository) {
        this.repository = repository;
    }

    public void execute(Long id, String tenantId) {
        repository
                .findById(id)
                .orElseThrow(
                        () ->
                                new EntityNotFoundException(
                                        "InstitutionalRoleCatalog", id.toString()));
        repository.deleteById(id);
    }
}

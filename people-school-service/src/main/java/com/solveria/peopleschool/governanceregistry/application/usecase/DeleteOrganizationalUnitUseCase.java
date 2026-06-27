package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.governanceregistry.domain.orgunit.OrganizationalUnitRepositoryPort;

public class DeleteOrganizationalUnitUseCase {

    private final OrganizationalUnitRepositoryPort repository;

    public DeleteOrganizationalUnitUseCase(OrganizationalUnitRepositoryPort repository) {
        this.repository = repository;
    }

    public void execute(Long id, String tenantId) {
        repository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("OrganizationalUnit", id.toString()));
        repository.deleteById(id);
    }
}

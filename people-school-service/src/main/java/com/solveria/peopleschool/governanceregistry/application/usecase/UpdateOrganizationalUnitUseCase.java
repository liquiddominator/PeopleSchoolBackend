package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.governanceregistry.application.dto.UpdateOrganizationalUnitRequest;
import com.solveria.peopleschool.governanceregistry.domain.orgunit.OrganizationalUnit;
import com.solveria.peopleschool.governanceregistry.domain.orgunit.OrganizationalUnitRepositoryPort;

public class UpdateOrganizationalUnitUseCase {

    private final OrganizationalUnitRepositoryPort repository;

    public UpdateOrganizationalUnitUseCase(OrganizationalUnitRepositoryPort repository) {
        this.repository = repository;
    }

    public OrganizationalUnit execute(
            Long id, UpdateOrganizationalUnitRequest request, String tenantId) {
        OrganizationalUnit existing =
                repository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new EntityNotFoundException(
                                                "OrganizationalUnit", id.toString()));
        OrganizationalUnit updated =
                new OrganizationalUnit(
                        existing.id(),
                        existing.educationUnitId(),
                        request.parentOrgUnitId(),
                        existing.orgUnitCode(),
                        request.orgUnitName(),
                        request.orgUnitType(),
                        request.displayOrder(),
                        request.unitScopeStatus(),
                        tenantId,
                        existing.createdAt(),
                        existing.createdBy(),
                        null,
                        null,
                        existing.version());
        return repository.save(updated);
    }
}

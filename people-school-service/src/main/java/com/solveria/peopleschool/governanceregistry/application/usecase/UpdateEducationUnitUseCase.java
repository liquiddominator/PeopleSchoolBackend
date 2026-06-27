package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.governanceregistry.application.dto.UpdateEducationUnitRequest;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnit;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnitRepositoryPort;

public class UpdateEducationUnitUseCase {

    private final EducationUnitRepositoryPort repository;

    public UpdateEducationUnitUseCase(EducationUnitRepositoryPort repository) {
        this.repository = repository;
    }

    public EducationUnit execute(Long id, UpdateEducationUnitRequest request, String tenantId) {
        EducationUnit existing =
                repository
                        .findById(id)
                        .orElseThrow(
                                () -> new EntityNotFoundException("EducationUnit", id.toString()));
        EducationUnit updated =
                new EducationUnit(
                        existing.id(),
                        request.unitCode(),
                        request.officialName(),
                        request.shortName(),
                        request.ministryRueCode(),
                        request.administrativeDependency() != null
                                ? request.administrativeDependency()
                                : existing.administrativeDependency(),
                        request.institutionalType() != null
                                ? request.institutionalType()
                                : existing.institutionalType(),
                        request.serviceType() != null
                                ? request.serviceType()
                                : existing.serviceType(),
                        request.phoneNumber(),
                        request.emailAddress(),
                        request.addressLine(),
                        request.unitStatus(),
                        tenantId,
                        existing.createdAt(),
                        existing.createdBy(),
                        null,
                        null,
                        existing.version());
        return repository.save(updated);
    }
}

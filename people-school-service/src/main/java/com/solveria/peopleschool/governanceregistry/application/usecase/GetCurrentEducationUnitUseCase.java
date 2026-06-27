package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnit;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnitRepositoryPort;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.UnitStatus;

public class GetCurrentEducationUnitUseCase {

    private final EducationUnitRepositoryPort repository;

    public GetCurrentEducationUnitUseCase(EducationUnitRepositoryPort repository) {
        this.repository = repository;
    }

    public EducationUnit execute(String tenantId) {
        return repository
                .findByTenantId(tenantId)
                .orElseGet(() -> repository.save(defaultUnit(tenantId)));
    }

    private EducationUnit defaultUnit(String tenantId) {
        return new EducationUnit(
                null,
                "SCHOOL-001",
                "Institución Educativa",
                null,
                null,
                "PRIVADO",
                "PRIVADO",
                "PRESENCIAL",
                null,
                null,
                null,
                UnitStatus.ACTIVE,
                tenantId,
                null,
                null,
                null,
                null,
                null);
    }
}

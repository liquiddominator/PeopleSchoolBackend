package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.core.shared.exceptions.BusinessRuleViolationException;
import com.solveria.peopleschool.governanceregistry.application.dto.CreateRoleCatalogRequest;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnit;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnitRepositoryPort;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.UnitStatus;
import com.solveria.peopleschool.governanceregistry.domain.rolecatalog.InstitutionalRoleCatalog;
import com.solveria.peopleschool.governanceregistry.domain.rolecatalog.RoleCatalogRepositoryPort;

public class CreateRoleCatalogUseCase {

    private final RoleCatalogRepositoryPort repository;
    private final EducationUnitRepositoryPort educationUnitRepository;

    public CreateRoleCatalogUseCase(
            RoleCatalogRepositoryPort repository,
            EducationUnitRepositoryPort educationUnitRepository) {
        this.repository = repository;
        this.educationUnitRepository = educationUnitRepository;
    }

    public InstitutionalRoleCatalog execute(CreateRoleCatalogRequest request, String tenantId) {
        if (repository.existsByRoleCodeAndTenantId(request.roleCode(), tenantId)) {
            throw new BusinessRuleViolationException("role.catalog.code.already.exists");
        }
        EducationUnit educationUnit = resolveEducationUnit(tenantId);
        InstitutionalRoleCatalog role =
                new InstitutionalRoleCatalog(
                        null,
                        educationUnit.id(),
                        request.roleCode(),
                        request.roleName(),
                        request.roleFamily(),
                        request.isAssignableToPerson(),
                        request.roleCatalogStatus(),
                        request.displayOrder(),
                        tenantId,
                        null,
                        null,
                        null,
                        null,
                        null);
        return repository.save(role);
    }

    private EducationUnit resolveEducationUnit(String tenantId) {
        return educationUnitRepository
                .findByTenantId(tenantId)
                .orElseGet(
                        () ->
                                educationUnitRepository.save(
                                        new EducationUnit(
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
                                                null)));
    }
}

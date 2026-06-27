package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.core.shared.exceptions.BusinessRuleViolationException;
import com.solveria.peopleschool.governanceregistry.application.dto.CreateOrganizationalUnitRequest;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnit;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnitRepositoryPort;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.UnitStatus;
import com.solveria.peopleschool.governanceregistry.domain.orgunit.OrganizationalUnit;
import com.solveria.peopleschool.governanceregistry.domain.orgunit.OrganizationalUnitRepositoryPort;

public class CreateOrganizationalUnitUseCase {

    private final OrganizationalUnitRepositoryPort repository;
    private final EducationUnitRepositoryPort educationUnitRepository;

    public CreateOrganizationalUnitUseCase(
            OrganizationalUnitRepositoryPort repository,
            EducationUnitRepositoryPort educationUnitRepository) {
        this.repository = repository;
        this.educationUnitRepository = educationUnitRepository;
    }

    public OrganizationalUnit execute(CreateOrganizationalUnitRequest request, String tenantId) {
        if (repository.existsByOrgUnitCodeAndTenantId(request.orgUnitCode(), tenantId)) {
            throw new BusinessRuleViolationException("org.unit.code.already.exists");
        }
        EducationUnit educationUnit = resolveEducationUnit(tenantId);
        OrganizationalUnit unit =
                new OrganizationalUnit(
                        null,
                        educationUnit.id(),
                        request.parentOrgUnitId(),
                        request.orgUnitCode(),
                        request.orgUnitName(),
                        request.orgUnitType(),
                        request.displayOrder(),
                        request.unitScopeStatus(),
                        tenantId,
                        null,
                        null,
                        null,
                        null,
                        null);
        return repository.save(unit);
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

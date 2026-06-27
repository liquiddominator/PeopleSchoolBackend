package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.core.shared.exceptions.BusinessRuleViolationException;
import com.solveria.peopleschool.governanceregistry.application.dto.CreateUnitSiteRequest;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnit;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnitRepositoryPort;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.UnitStatus;
import com.solveria.peopleschool.governanceregistry.domain.unitsite.UnitSite;
import com.solveria.peopleschool.governanceregistry.domain.unitsite.UnitSiteRepositoryPort;

public class CreateUnitSiteUseCase {

    private final UnitSiteRepositoryPort repository;
    private final EducationUnitRepositoryPort educationUnitRepository;

    public CreateUnitSiteUseCase(
            UnitSiteRepositoryPort repository,
            EducationUnitRepositoryPort educationUnitRepository) {
        this.repository = repository;
        this.educationUnitRepository = educationUnitRepository;
    }

    public UnitSite execute(CreateUnitSiteRequest request, String tenantId) {
        if (repository.existsBySiteCodeAndTenantId(request.siteCode(), tenantId)) {
            throw new BusinessRuleViolationException("unit.site.code.already.exists");
        }

        EducationUnit educationUnit = resolveEducationUnit(tenantId);
        if (request.isMainSite()) {
            repository.clearMainSiteForTenant(tenantId);
        }

        UnitSite site =
                new UnitSite(
                        null,
                        request.educationUnitId() != null
                                ? request.educationUnitId()
                                : educationUnit.id(),
                        request.siteCode(),
                        request.siteName(),
                        request.addressLine(),
                        request.cityName(),
                        request.departmentName(),
                        request.phoneNumber(),
                        request.emailAddress(),
                        request.isMainSite(),
                        request.siteStatus(),
                        tenantId,
                        null,
                        null,
                        null,
                        null,
                        null);
        return repository.save(site);
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

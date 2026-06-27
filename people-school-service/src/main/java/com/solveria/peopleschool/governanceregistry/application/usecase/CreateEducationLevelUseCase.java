package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.core.shared.exceptions.BusinessRuleViolationException;
import com.solveria.peopleschool.governanceregistry.application.dto.CreateEducationLevelRequest;
import com.solveria.peopleschool.governanceregistry.domain.educationlevel.EducationLevel;
import com.solveria.peopleschool.governanceregistry.domain.educationlevel.EducationLevelRepositoryPort;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnit;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnitRepositoryPort;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.UnitStatus;

public class CreateEducationLevelUseCase {

    private final EducationLevelRepositoryPort repository;
    private final EducationUnitRepositoryPort educationUnitRepository;

    public CreateEducationLevelUseCase(
            EducationLevelRepositoryPort repository,
            EducationUnitRepositoryPort educationUnitRepository) {
        this.repository = repository;
        this.educationUnitRepository = educationUnitRepository;
    }

    public EducationLevel execute(CreateEducationLevelRequest request, String tenantId) {
        if (repository.existsByLevelCodeAndTenantId(request.levelCode(), tenantId)) {
            throw new BusinessRuleViolationException("education.level.code.already.exists");
        }
        EducationUnit educationUnit = resolveEducationUnit(tenantId);
        EducationLevel level =
                new EducationLevel(
                        null,
                        educationUnit.id(),
                        request.levelCode(),
                        request.levelName(),
                        request.officialReferenceCode(),
                        request.levelSequence(),
                        request.levelStatus(),
                        tenantId,
                        null,
                        null,
                        null,
                        null,
                        null);
        return repository.save(level);
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

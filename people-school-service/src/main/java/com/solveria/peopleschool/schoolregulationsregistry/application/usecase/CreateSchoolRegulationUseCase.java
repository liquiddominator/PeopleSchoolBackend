package com.solveria.peopleschool.schoolregulationsregistry.application.usecase;

import com.solveria.core.shared.exceptions.BusinessRuleViolationException;
import com.solveria.peopleschool.schoolregulationsregistry.application.dto.CreateSchoolRegulationRequest;
import com.solveria.peopleschool.schoolregulationsregistry.application.port.out.EducationUnitReferencePort;
import com.solveria.peopleschool.schoolregulationsregistry.domain.schoolregulation.RegulationStatus;
import com.solveria.peopleschool.schoolregulationsregistry.domain.schoolregulation.SchoolRegulation;
import com.solveria.peopleschool.schoolregulationsregistry.domain.schoolregulation.SchoolRegulationRepositoryPort;

public class CreateSchoolRegulationUseCase {

    private final SchoolRegulationRepositoryPort repository;
    private final EducationUnitReferencePort educationUnitReferencePort;

    public CreateSchoolRegulationUseCase(
            SchoolRegulationRepositoryPort repository,
            EducationUnitReferencePort educationUnitReferencePort) {
        this.repository = repository;
        this.educationUnitReferencePort = educationUnitReferencePort;
    }

    public SchoolRegulation execute(CreateSchoolRegulationRequest request, String tenantId) {
        if (repository.existsByRegulationCodeAndTenantId(request.regulationCode(), tenantId)) {
            throw new BusinessRuleViolationException("school.regulation.code.already.exists");
        }

        EducationUnitReferencePort.EducationUnitReference educationUnit =
                resolveEducationUnit(tenantId);
        SchoolRegulation regulation =
                new SchoolRegulation(
                        null,
                        educationUnit.id(),
                        request.regulationCode(),
                        request.title(),
                        request.description(),
                        request.regulationType(),
                        request.issuingAuthorityTypeCode(),
                        request.criticalityLevel(),
                        RegulationStatus.DRAFT,
                        tenantId,
                        null,
                        null,
                        null,
                        null,
                        null);
        return repository.save(regulation);
    }

    private EducationUnitReferencePort.EducationUnitReference resolveEducationUnit(
            String tenantId) {
        EducationUnitReferencePort.EducationUnitReference educationUnit =
                educationUnitReferencePort
                        .findByTenantId(tenantId)
                        .orElseThrow(
                                () ->
                                        new BusinessRuleViolationException(
                                                "school.regulation.education.unit.not.found"));
        if (!educationUnit.active()) {
            throw new BusinessRuleViolationException("school.regulation.education.unit.inactive");
        }
        return educationUnit;
    }
}

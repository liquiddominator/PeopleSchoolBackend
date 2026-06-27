package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.core.shared.exceptions.BusinessRuleViolationException;
import com.solveria.peopleschool.governanceregistry.application.dto.CreateSchoolYearRequest;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnit;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnitRepositoryPort;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.UnitStatus;
import com.solveria.peopleschool.governanceregistry.domain.schoolyear.SchoolYear;
import com.solveria.peopleschool.governanceregistry.domain.schoolyear.SchoolYearRepositoryPort;

public class CreateSchoolYearUseCase {

    private final SchoolYearRepositoryPort schoolYearRepository;
    private final EducationUnitRepositoryPort educationUnitRepository;

    public CreateSchoolYearUseCase(
            SchoolYearRepositoryPort schoolYearRepository,
            EducationUnitRepositoryPort educationUnitRepository) {
        this.schoolYearRepository = schoolYearRepository;
        this.educationUnitRepository = educationUnitRepository;
    }

    public SchoolYear execute(CreateSchoolYearRequest request, String tenantId) {
        if (schoolYearRepository.existsBySchoolYearCodeAndTenantId(
                request.schoolYearCode(), tenantId)) {
            throw new BusinessRuleViolationException("school.year.code.already.exists");
        }
        EducationUnit educationUnit =
                educationUnitRepository
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
        SchoolYear schoolYear =
                new SchoolYear(
                        null,
                        educationUnit.id(),
                        request.schoolYearCode(),
                        request.schoolYearName(),
                        request.startDate(),
                        request.endDate(),
                        request.lifecycleStatus(),
                        false,
                        true,
                        true,
                        true,
                        false,
                        0,
                        tenantId,
                        null,
                        null,
                        null,
                        null,
                        null);
        return schoolYearRepository.save(schoolYear);
    }
}

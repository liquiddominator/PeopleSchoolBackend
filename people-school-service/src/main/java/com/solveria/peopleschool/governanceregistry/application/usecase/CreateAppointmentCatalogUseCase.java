package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.core.shared.exceptions.BusinessRuleViolationException;
import com.solveria.peopleschool.governanceregistry.application.dto.CreateAppointmentCatalogRequest;
import com.solveria.peopleschool.governanceregistry.domain.appointmentcatalog.AppointmentCatalog;
import com.solveria.peopleschool.governanceregistry.domain.appointmentcatalog.AppointmentCatalogRepositoryPort;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnit;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnitRepositoryPort;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.UnitStatus;

public class CreateAppointmentCatalogUseCase {

    private final AppointmentCatalogRepositoryPort repository;
    private final EducationUnitRepositoryPort educationUnitRepository;

    public CreateAppointmentCatalogUseCase(
            AppointmentCatalogRepositoryPort repository,
            EducationUnitRepositoryPort educationUnitRepository) {
        this.repository = repository;
        this.educationUnitRepository = educationUnitRepository;
    }

    public AppointmentCatalog execute(CreateAppointmentCatalogRequest request, String tenantId) {
        if (repository.existsByAppointmentCodeAndTenantId(request.appointmentCode(), tenantId)) {
            throw new BusinessRuleViolationException("appointment.catalog.code.already.exists");
        }
        EducationUnit educationUnit = resolveEducationUnit(tenantId);
        AppointmentCatalog appointment =
                new AppointmentCatalog(
                        null,
                        educationUnit.id(),
                        request.appointmentCode(),
                        request.appointmentName(),
                        request.orgUnitTypeScope(),
                        request.requiresDocumentSupport(),
                        request.appointmentCatalogStatus(),
                        request.displayOrder(),
                        tenantId,
                        null,
                        null,
                        null,
                        null,
                        null);
        return repository.save(appointment);
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

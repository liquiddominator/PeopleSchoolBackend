package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.governanceregistry.application.dto.UpdateAppointmentCatalogRequest;
import com.solveria.peopleschool.governanceregistry.domain.appointmentcatalog.AppointmentCatalog;
import com.solveria.peopleschool.governanceregistry.domain.appointmentcatalog.AppointmentCatalogRepositoryPort;

public class UpdateAppointmentCatalogUseCase {

    private final AppointmentCatalogRepositoryPort repository;

    public UpdateAppointmentCatalogUseCase(AppointmentCatalogRepositoryPort repository) {
        this.repository = repository;
    }

    public AppointmentCatalog execute(
            Long id, UpdateAppointmentCatalogRequest request, String tenantId) {
        AppointmentCatalog existing =
                repository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new EntityNotFoundException(
                                                "AppointmentCatalog", id.toString()));
        AppointmentCatalog updated =
                new AppointmentCatalog(
                        existing.id(),
                        existing.educationUnitId(),
                        existing.appointmentCode(),
                        request.appointmentName(),
                        request.orgUnitTypeScope(),
                        request.requiresDocumentSupport(),
                        request.appointmentCatalogStatus(),
                        request.displayOrder(),
                        tenantId,
                        existing.createdAt(),
                        existing.createdBy(),
                        null,
                        null,
                        existing.version());
        return repository.save(updated);
    }
}

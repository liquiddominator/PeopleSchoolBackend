package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.governanceregistry.domain.appointmentcatalog.AppointmentCatalogRepositoryPort;

public class DeleteAppointmentCatalogUseCase {

    private final AppointmentCatalogRepositoryPort repository;

    public DeleteAppointmentCatalogUseCase(AppointmentCatalogRepositoryPort repository) {
        this.repository = repository;
    }

    public void execute(Long id, String tenantId) {
        repository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("AppointmentCatalog", id.toString()));
        repository.deleteById(id);
    }
}

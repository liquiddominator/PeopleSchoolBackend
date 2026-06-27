package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.peopleschool.governanceregistry.domain.appointmentcatalog.AppointmentCatalog;
import com.solveria.peopleschool.governanceregistry.domain.appointmentcatalog.AppointmentCatalogRepositoryPort;
import java.util.List;

public class GetAppointmentCatalogUseCase {

    private final AppointmentCatalogRepositoryPort repository;

    public GetAppointmentCatalogUseCase(AppointmentCatalogRepositoryPort repository) {
        this.repository = repository;
    }

    public List<AppointmentCatalog> execute(String tenantId) {
        return repository.findAllByTenantId(tenantId);
    }
}

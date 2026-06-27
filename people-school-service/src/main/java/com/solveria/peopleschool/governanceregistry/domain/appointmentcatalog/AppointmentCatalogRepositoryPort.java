package com.solveria.peopleschool.governanceregistry.domain.appointmentcatalog;

import java.util.List;
import java.util.Optional;

public interface AppointmentCatalogRepositoryPort {
    AppointmentCatalog save(AppointmentCatalog appointment);

    Optional<AppointmentCatalog> findById(Long id);

    List<AppointmentCatalog> findAllByTenantId(String tenantId);

    void deleteById(Long id);

    boolean existsByAppointmentCodeAndTenantId(String code, String tenantId);
}

package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentCatalogJpaRepository
        extends JpaRepository<AppointmentCatalogJpaEntity, Long> {
    List<AppointmentCatalogJpaEntity> findAllByTenantIdOrderByDisplayOrderAsc(String tenantId);

    boolean existsByAppointmentCodeAndTenantId(String code, String tenantId);
}

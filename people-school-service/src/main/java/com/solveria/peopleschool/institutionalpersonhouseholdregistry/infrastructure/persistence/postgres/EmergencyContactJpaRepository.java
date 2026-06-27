package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyContactJpaRepository
        extends JpaRepository<EmergencyContactJpaEntity, Long> {
    List<EmergencyContactJpaEntity> findAllByPersonIdAndTenantIdOrderByEmergencyPriorityAsc(
            Long personId, String tenantId);
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonSensitiveCoverageJpaRepository
        extends JpaRepository<PersonSensitiveCoverageJpaEntity, Long> {
    List<PersonSensitiveCoverageJpaEntity> findAllByPersonIdAndTenantId(
            Long personId, String tenantId);
}

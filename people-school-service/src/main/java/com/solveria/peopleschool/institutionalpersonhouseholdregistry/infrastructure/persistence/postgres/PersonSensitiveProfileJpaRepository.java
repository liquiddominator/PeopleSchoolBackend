package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonSensitiveProfileJpaRepository
        extends JpaRepository<PersonSensitiveProfileJpaEntity, Long> {
    Optional<PersonSensitiveProfileJpaEntity> findByPersonIdAndTenantId(
            Long personId, String tenantId);
}

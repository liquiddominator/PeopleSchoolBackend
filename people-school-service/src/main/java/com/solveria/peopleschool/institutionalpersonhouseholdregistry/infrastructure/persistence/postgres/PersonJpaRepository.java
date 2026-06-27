package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonJpaRepository
        extends JpaRepository<PersonJpaEntity, Long>, JpaSpecificationExecutor<PersonJpaEntity> {

    boolean existsByPersonCodeAndTenantId(String personCode, String tenantId);

    long countByTenantIdAndCoreStatus(String tenantId, String coreStatus);
}

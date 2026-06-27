package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HouseholdJpaRepository
        extends JpaRepository<HouseholdJpaEntity, Long>,
                JpaSpecificationExecutor<HouseholdJpaEntity> {

    boolean existsByHouseholdCodeAndTenantId(String householdCode, String tenantId);

    long countByTenantIdAndHouseholdStatus(String tenantId, String householdStatus);
}

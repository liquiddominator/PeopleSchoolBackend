package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PickupAuthorizationJpaRepository
        extends JpaRepository<PickupAuthorizationJpaEntity, Long> {
    List<PickupAuthorizationJpaEntity> findAllByStudentPersonIdAndTenantId(
            Long studentPersonId, String tenantId);
}

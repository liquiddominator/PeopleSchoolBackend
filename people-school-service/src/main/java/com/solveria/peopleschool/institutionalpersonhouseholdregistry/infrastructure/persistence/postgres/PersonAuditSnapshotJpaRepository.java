package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonAuditSnapshotJpaRepository
        extends JpaRepository<PersonAuditSnapshotJpaEntity, Long> {
    List<PersonAuditSnapshotJpaEntity> findAllByPersonIdAndTenantId(Long personId, String tenantId);
}

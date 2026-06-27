package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonConflictJpaRepository extends JpaRepository<PersonConflictJpaEntity, Long> {

    List<PersonConflictJpaEntity> findByTenantId(String tenantId);

    long countByTenantIdAndConflictStatusIn(String tenantId, List<String> conflictStatuses);
}

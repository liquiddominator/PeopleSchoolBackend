package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuardianRelationshipJpaRepository
        extends JpaRepository<GuardianRelationshipJpaEntity, Long> {
    List<GuardianRelationshipJpaEntity> findAllByStudentPersonIdAndTenantId(
            Long studentPersonId, String tenantId);

    List<GuardianRelationshipJpaEntity> findAllByGuardianPersonIdAndTenantId(
            Long guardianPersonId, String tenantId);

    long countByTenantId(String tenantId);
}

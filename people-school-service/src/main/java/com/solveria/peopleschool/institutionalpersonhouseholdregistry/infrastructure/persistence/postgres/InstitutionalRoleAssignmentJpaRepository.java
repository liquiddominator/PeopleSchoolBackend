package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionalRoleAssignmentJpaRepository
        extends JpaRepository<InstitutionalRoleAssignmentJpaEntity, Long> {
    List<InstitutionalRoleAssignmentJpaEntity> findAllByPersonIdAndTenantId(
            Long personId, String tenantId);

    boolean existsByPersonIdAndRoleCodeAndTenantId(Long personId, String roleCode, String tenantId);
}

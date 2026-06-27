package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgGroupJpaRepository extends JpaRepository<OrgGroupJpaEntity, Long> {
    Optional<OrgGroupJpaEntity> findByTenantId(String tenantId);

    boolean existsByTenantCodeAndTenantId(String tenantCode, String tenantId);
}

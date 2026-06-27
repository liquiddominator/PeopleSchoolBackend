package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleCatalogJpaRepository extends JpaRepository<RoleCatalogJpaEntity, Long> {
    List<RoleCatalogJpaEntity> findAllByTenantIdOrderByDisplayOrderAsc(String tenantId);

    boolean existsByRoleCodeAndTenantId(String code, String tenantId);
}

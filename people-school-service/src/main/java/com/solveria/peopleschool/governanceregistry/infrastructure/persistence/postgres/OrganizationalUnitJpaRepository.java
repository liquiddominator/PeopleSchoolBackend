package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationalUnitJpaRepository
        extends JpaRepository<OrganizationalUnitJpaEntity, Long> {
    List<OrganizationalUnitJpaEntity> findAllByTenantIdOrderByDisplayOrderAsc(String tenantId);

    boolean existsByOrgUnitCodeAndTenantId(String code, String tenantId);
}

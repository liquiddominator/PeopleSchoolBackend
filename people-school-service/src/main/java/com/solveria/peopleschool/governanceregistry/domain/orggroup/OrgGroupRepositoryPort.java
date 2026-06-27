package com.solveria.peopleschool.governanceregistry.domain.orggroup;

import java.util.Optional;

public interface OrgGroupRepositoryPort {
    OrgGroup save(OrgGroup orgGroup);

    Optional<OrgGroup> findById(Long id);

    Optional<OrgGroup> findByTenantId(String tenantId);

    boolean existsByTenantCodeAndTenantId(String tenantCode, String tenantId);
}

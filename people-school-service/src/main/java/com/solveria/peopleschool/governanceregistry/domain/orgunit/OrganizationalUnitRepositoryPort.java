package com.solveria.peopleschool.governanceregistry.domain.orgunit;

import java.util.List;
import java.util.Optional;

public interface OrganizationalUnitRepositoryPort {
    OrganizationalUnit save(OrganizationalUnit unit);

    Optional<OrganizationalUnit> findById(Long id);

    List<OrganizationalUnit> findAllByTenantId(String tenantId);

    void deleteById(Long id);

    boolean existsByOrgUnitCodeAndTenantId(String code, String tenantId);
}

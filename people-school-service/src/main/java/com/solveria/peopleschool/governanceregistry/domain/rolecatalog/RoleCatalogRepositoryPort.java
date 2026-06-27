package com.solveria.peopleschool.governanceregistry.domain.rolecatalog;

import java.util.List;
import java.util.Optional;

public interface RoleCatalogRepositoryPort {
    InstitutionalRoleCatalog save(InstitutionalRoleCatalog role);

    Optional<InstitutionalRoleCatalog> findById(Long id);

    List<InstitutionalRoleCatalog> findAllByTenantId(String tenantId);

    void deleteById(Long id);

    boolean existsByRoleCodeAndTenantId(String code, String tenantId);
}

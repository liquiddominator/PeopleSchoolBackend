package com.solveria.peopleschool.governanceregistry.domain.unitsite;

import java.util.List;
import java.util.Optional;

public interface UnitSiteRepositoryPort {
    UnitSite save(UnitSite site);

    Optional<UnitSite> findById(Long id);

    List<UnitSite> findAllByTenantId(String tenantId);

    boolean existsBySiteCodeAndTenantId(String siteCode, String tenantId);

    void clearMainSiteForTenant(String tenantId);

    void deleteById(Long id);
}

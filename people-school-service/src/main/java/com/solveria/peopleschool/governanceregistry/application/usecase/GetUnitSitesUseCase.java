package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.peopleschool.governanceregistry.domain.unitsite.UnitSite;
import com.solveria.peopleschool.governanceregistry.domain.unitsite.UnitSiteRepositoryPort;
import java.util.List;

public class GetUnitSitesUseCase {

    private final UnitSiteRepositoryPort repository;

    public GetUnitSitesUseCase(UnitSiteRepositoryPort repository) {
        this.repository = repository;
    }

    public List<UnitSite> execute(String tenantId) {
        return repository.findAllByTenantId(tenantId);
    }
}

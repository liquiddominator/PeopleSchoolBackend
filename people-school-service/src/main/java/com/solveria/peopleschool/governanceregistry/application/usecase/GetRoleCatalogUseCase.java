package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.peopleschool.governanceregistry.domain.rolecatalog.InstitutionalRoleCatalog;
import com.solveria.peopleschool.governanceregistry.domain.rolecatalog.RoleCatalogRepositoryPort;
import java.util.List;

public class GetRoleCatalogUseCase {

    private final RoleCatalogRepositoryPort repository;

    public GetRoleCatalogUseCase(RoleCatalogRepositoryPort repository) {
        this.repository = repository;
    }

    public List<InstitutionalRoleCatalog> execute(String tenantId) {
        return repository.findAllByTenantId(tenantId);
    }
}

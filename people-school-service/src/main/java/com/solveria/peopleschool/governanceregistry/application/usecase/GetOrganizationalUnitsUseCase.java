package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.peopleschool.governanceregistry.domain.orgunit.OrganizationalUnit;
import com.solveria.peopleschool.governanceregistry.domain.orgunit.OrganizationalUnitRepositoryPort;
import java.util.List;

public class GetOrganizationalUnitsUseCase {

    private final OrganizationalUnitRepositoryPort repository;

    public GetOrganizationalUnitsUseCase(OrganizationalUnitRepositoryPort repository) {
        this.repository = repository;
    }

    public List<OrganizationalUnit> execute(String tenantId) {
        return repository.findAllByTenantId(tenantId);
    }
}

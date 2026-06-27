package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.peopleschool.governanceregistry.domain.orggroup.OrgGroup;
import com.solveria.peopleschool.governanceregistry.domain.orggroup.OrgGroupRepositoryPort;
import com.solveria.peopleschool.governanceregistry.domain.orggroup.OrgGroupStatus;

public class GetCurrentOrgGroupUseCase {

    private final OrgGroupRepositoryPort repository;

    public GetCurrentOrgGroupUseCase(OrgGroupRepositoryPort repository) {
        this.repository = repository;
    }

    public OrgGroup execute(String tenantId) {
        return repository
                .findByTenantId(tenantId)
                .orElseGet(() -> repository.save(defaultGroup(tenantId)));
    }

    private OrgGroup defaultGroup(String tenantId) {
        return new OrgGroup(
                null,
                "ORG-001",
                "Organización Educativa",
                null,
                null,
                "BO",
                "BOB",
                "America/La_Paz",
                OrgGroupStatus.ACTIVE,
                tenantId,
                null,
                null,
                null,
                null,
                null);
    }
}

package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.core.shared.exceptions.BusinessRuleViolationException;
import com.solveria.peopleschool.governanceregistry.application.dto.CreateOrgGroupRequest;
import com.solveria.peopleschool.governanceregistry.domain.orggroup.OrgGroup;
import com.solveria.peopleschool.governanceregistry.domain.orggroup.OrgGroupRepositoryPort;

public class CreateOrgGroupUseCase {

    private final OrgGroupRepositoryPort repository;

    public CreateOrgGroupUseCase(OrgGroupRepositoryPort repository) {
        this.repository = repository;
    }

    public OrgGroup execute(CreateOrgGroupRequest request, String tenantId) {
        if (repository.existsByTenantCodeAndTenantId(request.tenantCode(), tenantId)) {
            throw new BusinessRuleViolationException("org.group.tenant.code.already.exists");
        }
        OrgGroup group =
                new OrgGroup(
                        null,
                        request.tenantCode(),
                        request.legalName(),
                        request.commercialName(),
                        request.taxIdentifier(),
                        request.countryCode(),
                        request.defaultCurrencyCode(),
                        request.defaultTimezone(),
                        request.orgGroupStatus(),
                        tenantId,
                        null,
                        null,
                        null,
                        null,
                        null);
        return repository.save(group);
    }
}

package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.core.shared.exceptions.BusinessRuleViolationException;
import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.governanceregistry.application.dto.UpdateOrgGroupRequest;
import com.solveria.peopleschool.governanceregistry.domain.orggroup.OrgGroup;
import com.solveria.peopleschool.governanceregistry.domain.orggroup.OrgGroupRepositoryPort;

public class UpdateOrgGroupUseCase {

    private final OrgGroupRepositoryPort repository;

    public UpdateOrgGroupUseCase(OrgGroupRepositoryPort repository) {
        this.repository = repository;
    }

    public OrgGroup execute(Long id, UpdateOrgGroupRequest request, String tenantId) {
        OrgGroup existing =
                repository
                        .findById(id)
                        .filter(g -> g.tenantId().equals(tenantId))
                        .orElseThrow(() -> new EntityNotFoundException("OrgGroup", id.toString()));

        if (!existing.tenantCode().equals(request.tenantCode())
                && repository.existsByTenantCodeAndTenantId(request.tenantCode(), tenantId)) {
            throw new BusinessRuleViolationException("org.group.tenant.code.already.exists");
        }

        OrgGroup updated =
                new OrgGroup(
                        existing.id(),
                        request.tenantCode(),
                        request.legalName(),
                        request.commercialName(),
                        request.taxIdentifier(),
                        request.countryCode(),
                        request.defaultCurrencyCode(),
                        request.defaultTimezone(),
                        request.orgGroupStatus(),
                        existing.tenantId(),
                        existing.createdAt(),
                        existing.createdBy(),
                        existing.lastModifiedAt(),
                        existing.lastModifiedBy(),
                        existing.version());
        return repository.save(updated);
    }
}

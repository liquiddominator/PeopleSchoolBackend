package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.core.shared.exceptions.BusinessRuleViolationException;
import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.governanceregistry.application.dto.UpdateUnitSiteRequest;
import com.solveria.peopleschool.governanceregistry.domain.unitsite.UnitSite;
import com.solveria.peopleschool.governanceregistry.domain.unitsite.UnitSiteRepositoryPort;

public class UpdateUnitSiteUseCase {

    private final UnitSiteRepositoryPort repository;

    public UpdateUnitSiteUseCase(UnitSiteRepositoryPort repository) {
        this.repository = repository;
    }

    public UnitSite execute(Long id, UpdateUnitSiteRequest request, String tenantId) {
        UnitSite existing =
                repository
                        .findById(id)
                        .filter(s -> s.tenantId().equals(tenantId))
                        .orElseThrow(() -> new EntityNotFoundException("UnitSite", id.toString()));

        if (!existing.siteCode().equals(request.siteCode())
                && repository.existsBySiteCodeAndTenantId(request.siteCode(), tenantId)) {
            throw new BusinessRuleViolationException("unit.site.code.already.exists");
        }

        if (request.isMainSite()) {
            repository.clearMainSiteForTenant(tenantId);
        }

        UnitSite updated =
                new UnitSite(
                        existing.id(),
                        existing.educationUnitId(),
                        request.siteCode(),
                        request.siteName(),
                        request.addressLine(),
                        request.cityName(),
                        request.departmentName(),
                        request.phoneNumber(),
                        request.emailAddress(),
                        request.isMainSite(),
                        request.siteStatus(),
                        existing.tenantId(),
                        existing.createdAt(),
                        existing.createdBy(),
                        existing.lastModifiedAt(),
                        existing.lastModifiedBy(),
                        existing.version());
        return repository.save(updated);
    }
}

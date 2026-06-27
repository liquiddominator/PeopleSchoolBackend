package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.governanceregistry.domain.unitsite.UnitSite;
import com.solveria.peopleschool.governanceregistry.domain.unitsite.UnitSiteRepositoryPort;

public class DeleteUnitSiteUseCase {

    private final UnitSiteRepositoryPort repository;

    public DeleteUnitSiteUseCase(UnitSiteRepositoryPort repository) {
        this.repository = repository;
    }

    public void execute(Long id, String tenantId) {
        UnitSite existing =
                repository
                        .findById(id)
                        .filter(s -> s.tenantId().equals(tenantId))
                        .orElseThrow(() -> new EntityNotFoundException("UnitSite", id.toString()));
        repository.deleteById(existing.id());
    }
}

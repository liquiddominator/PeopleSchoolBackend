package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalaffiliation.InstitutionalAffiliation;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalaffiliation.InstitutionalAffiliationRepositoryPort;
import java.util.List;

public class GetAffiliationsUseCase {

    private final InstitutionalAffiliationRepositoryPort repository;

    public GetAffiliationsUseCase(InstitutionalAffiliationRepositoryPort repository) {
        this.repository = repository;
    }

    public List<InstitutionalAffiliation> execute(Long personId, String tenantId) {
        return repository.findAllByPersonIdAndTenantId(personId, tenantId);
    }
}

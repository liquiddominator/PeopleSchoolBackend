package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.legalidentity.PersonLegalIdentity;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.legalidentity.PersonLegalIdentityRepositoryPort;
import java.util.List;

public class ListLegalIdentitiesUseCase {

    private final PersonLegalIdentityRepositoryPort identityRepository;

    public ListLegalIdentitiesUseCase(PersonLegalIdentityRepositoryPort identityRepository) {
        this.identityRepository = identityRepository;
    }

    public List<PersonLegalIdentity> execute(Long personId, String tenantId) {
        return identityRepository.findByPersonIdAndTenantId(personId, tenantId);
    }
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personidentitylink.PersonIdentityLink;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personidentitylink.PersonIdentityLinkRepositoryPort;
import java.util.List;

public class GetPersonIdentityLinksUseCase {

    private final PersonIdentityLinkRepositoryPort repository;

    public GetPersonIdentityLinksUseCase(PersonIdentityLinkRepositoryPort repository) {
        this.repository = repository;
    }

    public List<PersonIdentityLink> execute(Long personId, String tenantId) {
        return repository.findAllByPersonIdAndTenantId(personId, tenantId);
    }
}

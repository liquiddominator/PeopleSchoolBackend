package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreateIdentityLinkRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personidentitylink.IdentityLinkStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personidentitylink.PersonIdentityLink;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personidentitylink.PersonIdentityLinkRepositoryPort;
import java.time.LocalDateTime;

public class LinkPersonIdentityUseCase {

    private final PersonIdentityLinkRepositoryPort repository;

    public LinkPersonIdentityUseCase(PersonIdentityLinkRepositoryPort repository) {
        this.repository = repository;
    }

    public PersonIdentityLink execute(CreateIdentityLinkRequest request, String tenantId) {
        PersonIdentityLink link =
                new PersonIdentityLink(
                        null,
                        request.personId(),
                        request.iamSubjectId(),
                        request.identityProviderCode(),
                        IdentityLinkStatus.valueOf(request.identityLinkStatus()),
                        LocalDateTime.parse(request.linkedAt()),
                        request.linkedBy(),
                        tenantId,
                        null,
                        null,
                        null,
                        null,
                        null);
        return repository.save(link);
    }
}

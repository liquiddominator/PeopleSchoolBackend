package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.core.shared.exceptions.BusinessRuleViolationException;
import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.LegalIdentityRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.legalidentity.DocumentType;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.legalidentity.IdentityStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.legalidentity.PersonLegalIdentity;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.legalidentity.PersonLegalIdentityRepositoryPort;

public class UpdateLegalIdentityUseCase {

    private final PersonLegalIdentityRepositoryPort identityRepository;

    public UpdateLegalIdentityUseCase(PersonLegalIdentityRepositoryPort identityRepository) {
        this.identityRepository = identityRepository;
    }

    public PersonLegalIdentity execute(Long id, LegalIdentityRequest request, String tenantId) {
        PersonLegalIdentity existing =
                identityRepository
                        .findById(id)
                        .filter(i -> i.tenantId().equals(tenantId))
                        .orElseThrow(
                                () ->
                                        new EntityNotFoundException(
                                                "PersonLegalIdentity", id.toString()));

        DocumentType documentType = DocumentType.valueOf(request.tipo());
        if (identityRepository.existsByDocumentInTenantExcludingIdentityId(
                documentType, request.numero(), tenantId, existing.id())) {
            throw new BusinessRuleViolationException("person.legal.identity.already.exists");
        }

        PersonLegalIdentity updated =
                new PersonLegalIdentity(
                        existing.id(),
                        existing.personId(),
                        documentType,
                        request.numero(),
                        request.paisEmisor(),
                        request.esPrincipal(),
                        IdentityStatus.valueOf(request.estado()),
                        existing.tenantId(),
                        existing.version());
        return identityRepository.save(updated);
    }
}

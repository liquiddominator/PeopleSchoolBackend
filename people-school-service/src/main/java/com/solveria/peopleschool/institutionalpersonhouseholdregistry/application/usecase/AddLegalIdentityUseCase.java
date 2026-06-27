package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.core.shared.exceptions.BusinessRuleViolationException;
import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.LegalIdentityRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.legalidentity.DocumentType;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.legalidentity.IdentityStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.legalidentity.PersonLegalIdentity;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.legalidentity.PersonLegalIdentityRepositoryPort;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.PersonRepositoryPort;

public class AddLegalIdentityUseCase {

    private final PersonLegalIdentityRepositoryPort identityRepository;
    private final PersonRepositoryPort personRepository;

    public AddLegalIdentityUseCase(
            PersonLegalIdentityRepositoryPort identityRepository,
            PersonRepositoryPort personRepository) {
        this.identityRepository = identityRepository;
        this.personRepository = personRepository;
    }

    public PersonLegalIdentity execute(
            Long personId, LegalIdentityRequest request, String tenantId) {
        personRepository
                .findById(personId)
                .filter(p -> p.tenantId().equals(tenantId))
                .orElseThrow(() -> new EntityNotFoundException("Person", personId.toString()));

        DocumentType documentType = DocumentType.valueOf(request.tipo());
        if (identityRepository.existsByDocumentInTenant(documentType, request.numero(), tenantId)) {
            throw new BusinessRuleViolationException("person.legal.identity.already.exists");
        }

        PersonLegalIdentity identity =
                new PersonLegalIdentity(
                        null,
                        personId,
                        documentType,
                        request.numero(),
                        request.paisEmisor(),
                        request.esPrincipal(),
                        IdentityStatus.valueOf(request.estado()),
                        tenantId,
                        null);
        return identityRepository.save(identity);
    }
}

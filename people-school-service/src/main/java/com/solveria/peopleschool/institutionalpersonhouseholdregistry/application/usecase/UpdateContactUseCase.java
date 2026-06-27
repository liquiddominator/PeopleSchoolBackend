package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.ContactRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.contact.ContactType;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.contact.ContactUse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.contact.PersonContact;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.contact.PersonContactRepositoryPort;

public class UpdateContactUseCase {

    private final PersonContactRepositoryPort contactRepository;

    public UpdateContactUseCase(PersonContactRepositoryPort contactRepository) {
        this.contactRepository = contactRepository;
    }

    public PersonContact execute(Long id, ContactRequest request, String tenantId) {
        PersonContact existing =
                contactRepository
                        .findById(id)
                        .filter(c -> c.tenantId().equals(tenantId))
                        .orElseThrow(
                                () -> new EntityNotFoundException("PersonContact", id.toString()));

        PersonContact updated =
                new PersonContact(
                        existing.id(),
                        existing.personId(),
                        ContactType.valueOf(request.tipo()),
                        request.valor(),
                        ContactUse.valueOf(request.uso()),
                        request.esPrincipal(),
                        existing.tenantId(),
                        existing.version());
        return contactRepository.save(updated);
    }
}

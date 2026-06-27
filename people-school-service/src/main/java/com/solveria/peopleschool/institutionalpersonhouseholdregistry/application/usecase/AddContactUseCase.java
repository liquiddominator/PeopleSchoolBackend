package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.ContactRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.contact.ContactType;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.contact.ContactUse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.contact.PersonContact;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.contact.PersonContactRepositoryPort;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.PersonRepositoryPort;

public class AddContactUseCase {

    private final PersonContactRepositoryPort contactRepository;
    private final PersonRepositoryPort personRepository;

    public AddContactUseCase(
            PersonContactRepositoryPort contactRepository, PersonRepositoryPort personRepository) {
        this.contactRepository = contactRepository;
        this.personRepository = personRepository;
    }

    public PersonContact execute(Long personId, ContactRequest request, String tenantId) {
        personRepository
                .findById(personId)
                .filter(p -> p.tenantId().equals(tenantId))
                .orElseThrow(() -> new EntityNotFoundException("Person", personId.toString()));

        PersonContact contact =
                new PersonContact(
                        null,
                        personId,
                        ContactType.valueOf(request.tipo()),
                        request.valor(),
                        ContactUse.valueOf(request.uso()),
                        request.esPrincipal(),
                        tenantId,
                        null);
        return contactRepository.save(contact);
    }
}

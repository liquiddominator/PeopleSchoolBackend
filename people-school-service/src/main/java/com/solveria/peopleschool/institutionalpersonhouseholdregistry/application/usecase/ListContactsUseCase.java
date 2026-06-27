package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.contact.PersonContact;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.contact.PersonContactRepositoryPort;
import java.util.List;

public class ListContactsUseCase {

    private final PersonContactRepositoryPort contactRepository;

    public ListContactsUseCase(PersonContactRepositoryPort contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<PersonContact> execute(Long personId, String tenantId) {
        return contactRepository.findByPersonIdAndTenantId(personId, tenantId);
    }
}

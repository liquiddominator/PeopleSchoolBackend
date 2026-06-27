package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.contact.PersonContactRepositoryPort;

public class DeleteContactUseCase {

    private final PersonContactRepositoryPort contactRepository;

    public DeleteContactUseCase(PersonContactRepositoryPort contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void execute(Long id, String tenantId) {
        contactRepository
                .findById(id)
                .filter(c -> c.tenantId().equals(tenantId))
                .orElseThrow(() -> new EntityNotFoundException("PersonContact", id.toString()));
        contactRepository.deleteById(id);
    }
}

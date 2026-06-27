package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.PersonRepositoryPort;

public class DeletePersonUseCase {

    private final PersonRepositoryPort personRepository;

    public DeletePersonUseCase(PersonRepositoryPort personRepository) {
        this.personRepository = personRepository;
    }

    public void execute(Long id, String tenantId) {
        personRepository
                .findById(id)
                .filter(p -> p.tenantId().equals(tenantId))
                .orElseThrow(() -> new EntityNotFoundException("Person", id.toString()));
        personRepository.deleteById(id);
    }
}

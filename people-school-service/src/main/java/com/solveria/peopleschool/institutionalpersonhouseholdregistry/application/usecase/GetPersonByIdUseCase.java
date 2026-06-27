package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.Person;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.PersonRepositoryPort;

public class GetPersonByIdUseCase {

    private final PersonRepositoryPort personRepository;

    public GetPersonByIdUseCase(PersonRepositoryPort personRepository) {
        this.personRepository = personRepository;
    }

    public Person execute(Long id, String tenantId) {
        return personRepository
                .findById(id)
                .filter(p -> p.tenantId().equals(tenantId))
                .orElseThrow(() -> new EntityNotFoundException("Person", id.toString()));
    }
}

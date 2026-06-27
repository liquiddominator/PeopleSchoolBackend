package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.Person;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.PersonRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class GetPersonsUseCase {

    private final PersonRepositoryPort personRepository;

    public GetPersonsUseCase(PersonRepositoryPort personRepository) {
        this.personRepository = personRepository;
    }

    public Page<Person> execute(
            String tenantId,
            String personTypeCode,
            String coreStatus,
            String search,
            Pageable pageable) {
        return personRepository.findAll(tenantId, personTypeCode, coreStatus, search, pageable);
    }
}

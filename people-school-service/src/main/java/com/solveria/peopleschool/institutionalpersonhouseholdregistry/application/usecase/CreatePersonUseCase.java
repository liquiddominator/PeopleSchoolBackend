package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.core.shared.exceptions.BusinessRuleViolationException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreatePersonRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.Person;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.PersonRepositoryPort;

public class CreatePersonUseCase {

    private final PersonRepositoryPort personRepository;

    public CreatePersonUseCase(PersonRepositoryPort personRepository) {
        this.personRepository = personRepository;
    }

    public Person execute(CreatePersonRequest request, String tenantId) {
        if (personRepository.existsByPersonCodeAndTenantId(request.personCode(), tenantId)) {
            throw new BusinessRuleViolationException("person.code.already.exists");
        }
        Person person =
                new Person(
                        null,
                        request.personCode(),
                        request.personTypeCode(),
                        request.coreStatus(),
                        request.primaryPhotoAssetId(),
                        request.nombres(),
                        request.apellidos(),
                        tenantId,
                        null,
                        null,
                        null,
                        null,
                        null);
        return personRepository.save(person);
    }
}

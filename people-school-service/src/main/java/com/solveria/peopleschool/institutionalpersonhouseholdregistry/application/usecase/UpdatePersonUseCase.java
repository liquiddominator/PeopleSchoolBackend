package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.UpdatePersonRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.Person;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.PersonRepositoryPort;

public class UpdatePersonUseCase {

    private final PersonRepositoryPort personRepository;

    public UpdatePersonUseCase(PersonRepositoryPort personRepository) {
        this.personRepository = personRepository;
    }

    public Person execute(Long id, UpdatePersonRequest request, String tenantId) {
        Person existing =
                personRepository
                        .findById(id)
                        .filter(p -> p.tenantId().equals(tenantId))
                        .orElseThrow(() -> new EntityNotFoundException("Person", id.toString()));

        Person updated =
                new Person(
                        existing.id(),
                        existing.personCode(),
                        request.personTypeCode(),
                        request.coreStatus(),
                        request.primaryPhotoAssetId(),
                        request.nombres(),
                        request.apellidos(),
                        existing.tenantId(),
                        existing.createdAt(),
                        existing.createdBy(),
                        null,
                        null,
                        existing.version());
        return personRepository.save(updated);
    }
}

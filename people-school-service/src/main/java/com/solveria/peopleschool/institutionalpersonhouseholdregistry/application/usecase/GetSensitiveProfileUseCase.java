package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitiveprofile.PersonSensitiveProfile;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitiveprofile.PersonSensitiveProfileRepositoryPort;
import java.util.Optional;

public class GetSensitiveProfileUseCase {

    private final PersonSensitiveProfileRepositoryPort repository;

    public GetSensitiveProfileUseCase(PersonSensitiveProfileRepositoryPort repository) {
        this.repository = repository;
    }

    public Optional<PersonSensitiveProfile> execute(Long personId, String tenantId) {
        return repository.findByPersonIdAndTenantId(personId, tenantId);
    }
}

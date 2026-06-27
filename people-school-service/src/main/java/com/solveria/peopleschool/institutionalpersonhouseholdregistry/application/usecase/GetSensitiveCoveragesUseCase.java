package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitivecoverage.PersonSensitiveCoverage;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitivecoverage.PersonSensitiveCoverageRepositoryPort;
import java.util.List;

public class GetSensitiveCoveragesUseCase {

    private final PersonSensitiveCoverageRepositoryPort repository;

    public GetSensitiveCoveragesUseCase(PersonSensitiveCoverageRepositoryPort repository) {
        this.repository = repository;
    }

    public List<PersonSensitiveCoverage> execute(Long personId, String tenantId) {
        return repository.findAllByPersonIdAndTenantId(personId, tenantId);
    }
}

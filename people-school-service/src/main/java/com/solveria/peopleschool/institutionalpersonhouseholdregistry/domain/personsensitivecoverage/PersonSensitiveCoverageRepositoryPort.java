package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitivecoverage;

import java.util.List;

public interface PersonSensitiveCoverageRepositoryPort {
    List<PersonSensitiveCoverage> findAllByPersonIdAndTenantId(Long personId, String tenantId);

    PersonSensitiveCoverage save(PersonSensitiveCoverage coverage);
}

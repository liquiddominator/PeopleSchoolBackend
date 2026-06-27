package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitiveprofile;

import java.util.Optional;

public interface PersonSensitiveProfileRepositoryPort {
    Optional<PersonSensitiveProfile> findByPersonIdAndTenantId(Long personId, String tenantId);

    PersonSensitiveProfile save(PersonSensitiveProfile profile);
}

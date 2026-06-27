package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.contact;

import java.util.List;
import java.util.Optional;

public interface PersonContactRepositoryPort {
    List<PersonContact> findByPersonIdAndTenantId(Long personId, String tenantId);

    Optional<PersonContact> findById(Long id);

    PersonContact save(PersonContact domain);

    void deleteById(Long id);
}

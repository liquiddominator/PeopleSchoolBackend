package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.address;

import java.util.List;
import java.util.Optional;

public interface PersonAddressRepositoryPort {
    List<PersonAddress> findByPersonIdAndTenantId(Long personId, String tenantId);

    Optional<PersonAddress> findById(Long id);

    PersonAddress save(PersonAddress domain);

    void deleteById(Long id);
}

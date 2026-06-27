package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.pickupauthorization;

import java.util.List;
import java.util.Optional;

public interface PickupAuthorizationRepositoryPort {
    PickupAuthorization save(PickupAuthorization authorization);

    Optional<PickupAuthorization> findById(Long id);

    List<PickupAuthorization> findAllByStudentPersonIdAndTenantId(
            Long studentPersonId, String tenantId);

    void deleteById(Long id);
}

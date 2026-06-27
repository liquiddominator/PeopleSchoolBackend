package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.pickupauthorization.PickupAuthorization;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.pickupauthorization.PickupAuthorizationRepositoryPort;
import java.util.List;

public class GetPickupAuthorizationsUseCase {

    private final PickupAuthorizationRepositoryPort repository;

    public GetPickupAuthorizationsUseCase(PickupAuthorizationRepositoryPort repository) {
        this.repository = repository;
    }

    public List<PickupAuthorization> execute(Long studentPersonId, String tenantId) {
        return repository.findAllByStudentPersonIdAndTenantId(studentPersonId, tenantId);
    }
}

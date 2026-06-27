package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreatePickupAuthorizationRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.pickupauthorization.PickupAuthorization;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.pickupauthorization.PickupAuthorizationRepositoryPort;

public class RegisterPickupAuthorizationUseCase {

    private final PickupAuthorizationRepositoryPort repository;

    public RegisterPickupAuthorizationUseCase(PickupAuthorizationRepositoryPort repository) {
        this.repository = repository;
    }

    public PickupAuthorization execute(CreatePickupAuthorizationRequest request, String tenantId) {
        PickupAuthorization authorization =
                new PickupAuthorization(
                        null,
                        request.studentPersonId(),
                        request.authorizedPersonId(),
                        request.authorizationStatus(),
                        request.effectiveFrom(),
                        request.effectiveTo(),
                        request.authorizationScopeCode(),
                        request.notes(),
                        tenantId,
                        null,
                        null,
                        null,
                        null,
                        null);
        return repository.save(authorization);
    }
}

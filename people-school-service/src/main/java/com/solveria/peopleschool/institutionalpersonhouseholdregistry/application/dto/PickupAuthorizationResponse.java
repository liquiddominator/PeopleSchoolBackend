package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.pickupauthorization.AuthorizationStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.pickupauthorization.PickupAuthorization;
import java.time.LocalDate;

public record PickupAuthorizationResponse(
        Long id,
        Long studentPersonId,
        Long authorizedPersonId,
        AuthorizationStatus authorizationStatus,
        LocalDate effectiveFrom,
        LocalDate effectiveTo,
        String authorizationScopeCode,
        String notes) {

    public static PickupAuthorizationResponse from(PickupAuthorization a) {
        return new PickupAuthorizationResponse(
                a.id(),
                a.studentPersonId(),
                a.authorizedPersonId(),
                a.authorizationStatus(),
                a.effectiveFrom(),
                a.effectiveTo(),
                a.authorizationScopeCode(),
                a.notes());
    }
}

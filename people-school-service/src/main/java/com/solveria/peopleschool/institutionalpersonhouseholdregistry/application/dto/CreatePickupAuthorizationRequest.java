package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.pickupauthorization.AuthorizationStatus;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreatePickupAuthorizationRequest(
        @NotNull Long studentPersonId,
        @NotNull Long authorizedPersonId,
        @NotNull AuthorizationStatus authorizationStatus,
        LocalDate effectiveFrom,
        LocalDate effectiveTo,
        String authorizationScopeCode,
        String notes) {}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.pickupauthorization;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PickupAuthorization(
        Long id,
        Long studentPersonId,
        Long authorizedPersonId,
        AuthorizationStatus authorizationStatus,
        LocalDate effectiveFrom,
        LocalDate effectiveTo,
        String authorizationScopeCode,
        String notes,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

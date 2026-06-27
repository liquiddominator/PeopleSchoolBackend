package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household;

import java.time.LocalDateTime;

public record Household(
        Long id,
        String householdCode,
        String householdName,
        HouseholdTypeCode householdTypeCode,
        HouseholdStatus householdStatus,
        Long primaryBillingAddressId,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

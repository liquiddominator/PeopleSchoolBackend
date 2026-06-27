package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.Household;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.HouseholdStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.HouseholdTypeCode;
import java.time.LocalDateTime;

public record HouseholdResponse(
        Long id,
        String householdCode,
        String householdName,
        HouseholdTypeCode householdTypeCode,
        HouseholdStatus householdStatus,
        Long primaryBillingAddressId,
        String tenantId,
        LocalDateTime createdAt,
        LocalDateTime lastModifiedAt) {

    public static HouseholdResponse from(Household h) {
        return new HouseholdResponse(
                h.id(),
                h.householdCode(),
                h.householdName(),
                h.householdTypeCode(),
                h.householdStatus(),
                h.primaryBillingAddressId(),
                h.tenantId(),
                h.createdAt(),
                h.lastModifiedAt());
    }
}

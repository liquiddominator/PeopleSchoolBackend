package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.Household;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.HouseholdStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.HouseholdTypeCode;

public record HouseholdSummaryResponse(
        Long id,
        String householdCode,
        String householdName,
        HouseholdTypeCode householdTypeCode,
        HouseholdStatus householdStatus) {

    public static HouseholdSummaryResponse from(Household h) {
        return new HouseholdSummaryResponse(
                h.id(),
                h.householdCode(),
                h.householdName(),
                h.householdTypeCode(),
                h.householdStatus());
    }
}

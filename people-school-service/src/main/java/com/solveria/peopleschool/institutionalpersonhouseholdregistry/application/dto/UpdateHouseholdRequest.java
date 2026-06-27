package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.HouseholdStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.HouseholdTypeCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateHouseholdRequest(
        @NotBlank @Size(max = 255) String householdName,
        @NotNull HouseholdTypeCode householdTypeCode,
        @NotNull HouseholdStatus householdStatus,
        Long primaryBillingAddressId) {}

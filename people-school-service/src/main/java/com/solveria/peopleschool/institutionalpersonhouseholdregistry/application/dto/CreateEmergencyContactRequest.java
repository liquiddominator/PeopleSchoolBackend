package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.emergencycontact.EmergencyStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateEmergencyContactRequest(
        @NotNull Long personId,
        Long contactPersonId,
        @NotNull @Min(1) Integer emergencyPriority,
        String relationshipLabel,
        @NotNull EmergencyStatus emergencyStatus) {}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.emergencycontact.EmergencyContact;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.emergencycontact.EmergencyStatus;

public record EmergencyContactResponse(
        Long id,
        Long personId,
        Long contactPersonId,
        Integer emergencyPriority,
        String relationshipLabel,
        EmergencyStatus emergencyStatus) {

    public static EmergencyContactResponse from(EmergencyContact c) {
        return new EmergencyContactResponse(
                c.id(),
                c.personId(),
                c.contactPersonId(),
                c.emergencyPriority(),
                c.relationshipLabel(),
                c.emergencyStatus());
    }
}

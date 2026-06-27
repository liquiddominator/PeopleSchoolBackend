package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.emergencycontact;

import java.time.LocalDateTime;

public record EmergencyContact(
        Long id,
        Long personId,
        Long contactPersonId,
        Integer emergencyPriority,
        String relationshipLabel,
        EmergencyStatus emergencyStatus,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitiveprofile;

import java.time.LocalDateTime;

public record PersonSensitiveProfile(
        Long id,
        Long personId,
        String bloodTypeCode,
        String emergencyMedicalNotes,
        SensitiveProfileStatus sensitiveProfileStatus,
        LocalDateTime lastReviewedAt,
        String lastReviewedBy,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

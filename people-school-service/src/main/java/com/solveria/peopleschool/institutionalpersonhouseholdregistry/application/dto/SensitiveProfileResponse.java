package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitiveprofile.PersonSensitiveProfile;
import java.time.LocalDateTime;

public record SensitiveProfileResponse(
        Long id,
        Long personId,
        String bloodTypeCode,
        String emergencyMedicalNotes,
        String sensitiveProfileStatus,
        LocalDateTime lastReviewedAt,
        String lastReviewedBy,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {

    public static SensitiveProfileResponse from(PersonSensitiveProfile p) {
        return new SensitiveProfileResponse(
                p.id(),
                p.personId(),
                p.bloodTypeCode(),
                p.emergencyMedicalNotes(),
                p.sensitiveProfileStatus().name(),
                p.lastReviewedAt(),
                p.lastReviewedBy(),
                p.tenantId(),
                p.createdAt(),
                p.createdBy(),
                p.lastModifiedAt(),
                p.lastModifiedBy(),
                p.version());
    }
}

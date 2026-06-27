package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.UpdateSensitiveProfileRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitiveprofile.PersonSensitiveProfile;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitiveprofile.PersonSensitiveProfileRepositoryPort;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitiveprofile.SensitiveProfileStatus;
import java.time.LocalDateTime;

public class UpdateSensitiveProfileUseCase {

    private final PersonSensitiveProfileRepositoryPort repository;

    public UpdateSensitiveProfileUseCase(PersonSensitiveProfileRepositoryPort repository) {
        this.repository = repository;
    }

    public PersonSensitiveProfile execute(
            Long profileId, Long personId, UpdateSensitiveProfileRequest request, String tenantId) {
        PersonSensitiveProfile existing =
                repository
                        .findByPersonIdAndTenantId(personId, tenantId)
                        .filter(p -> p.id().equals(profileId))
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Sensitive profile not found for profileId="
                                                        + profileId));

        PersonSensitiveProfile updated =
                new PersonSensitiveProfile(
                        existing.id(),
                        existing.personId(),
                        request.bloodTypeCode(),
                        request.emergencyMedicalNotes(),
                        SensitiveProfileStatus.valueOf(request.sensitiveProfileStatus()),
                        request.lastReviewedAt() != null
                                ? LocalDateTime.parse(request.lastReviewedAt())
                                : null,
                        request.lastReviewedBy(),
                        existing.tenantId(),
                        existing.createdAt(),
                        existing.createdBy(),
                        existing.lastModifiedAt(),
                        existing.lastModifiedBy(),
                        existing.version());
        return repository.save(updated);
    }
}

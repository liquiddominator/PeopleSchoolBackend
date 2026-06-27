package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreateSensitiveProfileRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitiveprofile.PersonSensitiveProfile;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitiveprofile.PersonSensitiveProfileRepositoryPort;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitiveprofile.SensitiveProfileStatus;
import java.time.LocalDateTime;

public class CreateSensitiveProfileUseCase {

    private final PersonSensitiveProfileRepositoryPort repository;

    public CreateSensitiveProfileUseCase(PersonSensitiveProfileRepositoryPort repository) {
        this.repository = repository;
    }

    public PersonSensitiveProfile execute(CreateSensitiveProfileRequest request, String tenantId) {
        PersonSensitiveProfile profile =
                new PersonSensitiveProfile(
                        null,
                        request.personId(),
                        request.bloodTypeCode(),
                        request.emergencyMedicalNotes(),
                        SensitiveProfileStatus.valueOf(request.sensitiveProfileStatus()),
                        request.lastReviewedAt() != null
                                ? LocalDateTime.parse(request.lastReviewedAt())
                                : null,
                        request.lastReviewedBy(),
                        tenantId,
                        null,
                        null,
                        null,
                        null,
                        null);
        return repository.save(profile);
    }
}

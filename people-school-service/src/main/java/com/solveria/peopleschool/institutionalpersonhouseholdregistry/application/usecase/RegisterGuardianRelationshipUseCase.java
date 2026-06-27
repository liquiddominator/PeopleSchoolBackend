package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreateGuardianRelationshipRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.guardianrelationship.GuardianRelationship;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.guardianrelationship.GuardianRelationshipRepositoryPort;

public class RegisterGuardianRelationshipUseCase {

    private final GuardianRelationshipRepositoryPort repository;

    public RegisterGuardianRelationshipUseCase(GuardianRelationshipRepositoryPort repository) {
        this.repository = repository;
    }

    public GuardianRelationship execute(
            CreateGuardianRelationshipRequest request, String tenantId) {
        GuardianRelationship relationship =
                new GuardianRelationship(
                        null,
                        request.studentPersonId(),
                        request.guardianPersonId(),
                        request.householdId(),
                        request.guardianTypeCode(),
                        request.legalAuthorityStatus(),
                        request.schoolAuthorityStatus(),
                        request.effectiveFrom(),
                        request.effectiveTo(),
                        request.notes(),
                        tenantId,
                        null,
                        null,
                        null,
                        null,
                        null);
        return repository.save(relationship);
    }
}

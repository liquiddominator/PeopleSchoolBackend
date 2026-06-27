package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.guardianrelationship.GuardianRelationship;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.guardianrelationship.GuardianRelationshipRepositoryPort;
import java.util.List;

public class GetGuardianRelationshipsUseCase {

    private final GuardianRelationshipRepositoryPort repository;

    public GetGuardianRelationshipsUseCase(GuardianRelationshipRepositoryPort repository) {
        this.repository = repository;
    }

    public List<GuardianRelationship> executeByStudent(Long studentPersonId, String tenantId) {
        return repository.findAllByStudentPersonIdAndTenantId(studentPersonId, tenantId);
    }

    public List<GuardianRelationship> executeByGuardian(Long guardianPersonId, String tenantId) {
        return repository.findAllByGuardianPersonIdAndTenantId(guardianPersonId, tenantId);
    }
}

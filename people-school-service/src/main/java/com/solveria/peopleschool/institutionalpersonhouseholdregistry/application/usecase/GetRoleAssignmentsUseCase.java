package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalroleassignment.InstitutionalRoleAssignment;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalroleassignment.InstitutionalRoleAssignmentRepositoryPort;
import java.util.List;

public class GetRoleAssignmentsUseCase {

    private final InstitutionalRoleAssignmentRepositoryPort repository;

    public GetRoleAssignmentsUseCase(InstitutionalRoleAssignmentRepositoryPort repository) {
        this.repository = repository;
    }

    public List<InstitutionalRoleAssignment> execute(Long personId, String tenantId) {
        return repository.findAllByPersonIdAndTenantId(personId, tenantId);
    }
}

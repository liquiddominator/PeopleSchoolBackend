package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.core.shared.exceptions.BusinessRuleViolationException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreateRoleAssignmentRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalroleassignment.InstitutionalRoleAssignment;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalroleassignment.InstitutionalRoleAssignmentRepositoryPort;

public class AssignInstitutionalRoleUseCase {

    private final InstitutionalRoleAssignmentRepositoryPort repository;

    public AssignInstitutionalRoleUseCase(InstitutionalRoleAssignmentRepositoryPort repository) {
        this.repository = repository;
    }

    public InstitutionalRoleAssignment execute(
            CreateRoleAssignmentRequest request, String tenantId) {
        if (repository.existsByPersonIdAndRoleCodeAndTenantId(
                request.personId(), request.roleCode(), tenantId)) {
            throw new BusinessRuleViolationException("person.role.already.assigned");
        }
        InstitutionalRoleAssignment assignment =
                new InstitutionalRoleAssignment(
                        null,
                        request.personId(),
                        request.roleCode(),
                        request.roleStatus(),
                        request.effectiveFrom(),
                        request.effectiveTo(),
                        tenantId,
                        null,
                        null,
                        null,
                        null,
                        null);
        return repository.save(assignment);
    }
}

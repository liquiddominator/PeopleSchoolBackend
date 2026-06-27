package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalroleassignment;

import java.util.List;
import java.util.Optional;

public interface InstitutionalRoleAssignmentRepositoryPort {
    InstitutionalRoleAssignment save(InstitutionalRoleAssignment assignment);

    Optional<InstitutionalRoleAssignment> findById(Long id);

    List<InstitutionalRoleAssignment> findAllByPersonIdAndTenantId(Long personId, String tenantId);

    boolean existsByPersonIdAndRoleCodeAndTenantId(Long personId, String roleCode, String tenantId);

    void deleteById(Long id);
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.guardianrelationship;

import java.util.List;
import java.util.Optional;

public interface GuardianRelationshipRepositoryPort {
    GuardianRelationship save(GuardianRelationship relationship);

    Optional<GuardianRelationship> findById(Long id);

    List<GuardianRelationship> findAllByStudentPersonIdAndTenantId(
            Long studentPersonId, String tenantId);

    List<GuardianRelationship> findAllByGuardianPersonIdAndTenantId(
            Long guardianPersonId, String tenantId);

    long countByTenantId(String tenantId);

    void deleteById(Long id);
}

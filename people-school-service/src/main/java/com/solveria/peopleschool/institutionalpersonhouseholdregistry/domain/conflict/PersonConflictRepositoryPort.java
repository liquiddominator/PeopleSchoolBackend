package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.conflict;

import java.util.List;
import java.util.Optional;

public interface PersonConflictRepositoryPort {

    List<PersonConflict> findByTenantId(String tenantId);

    Optional<PersonConflict> findById(Long id);

    long countByTenantIdAndConflictStatusIn(
            String tenantId, java.util.List<ConflictStatus> statuses);

    PersonConflict save(PersonConflict conflict);
}

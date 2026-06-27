package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personauditsnapshot;

import java.util.List;

public interface PersonAuditSnapshotRepositoryPort {
    List<PersonAuditSnapshot> findAllByPersonIdAndTenantId(Long personId, String tenantId);
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personauditsnapshot.PersonAuditSnapshot;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personauditsnapshot.PersonAuditSnapshotRepositoryPort;
import java.util.List;

public class GetAuditSnapshotsUseCase {

    private final PersonAuditSnapshotRepositoryPort repository;

    public GetAuditSnapshotsUseCase(PersonAuditSnapshotRepositoryPort repository) {
        this.repository = repository;
    }

    public List<PersonAuditSnapshot> execute(Long personId, String tenantId) {
        return repository.findAllByPersonIdAndTenantId(personId, tenantId);
    }
}

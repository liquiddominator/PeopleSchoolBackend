package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personauditsnapshot.PersonAuditSnapshot;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personauditsnapshot.PersonAuditSnapshotRepositoryPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonAuditSnapshotPersistenceAdapter implements PersonAuditSnapshotRepositoryPort {

    private final PersonAuditSnapshotJpaRepository repository;
    private final PersonAuditSnapshotMapper mapper;

    @Override
    public List<PersonAuditSnapshot> findAllByPersonIdAndTenantId(Long personId, String tenantId) {
        return repository.findAllByPersonIdAndTenantId(personId, tenantId).stream()
                .map(mapper::toDomain)
                .toList();
    }
}

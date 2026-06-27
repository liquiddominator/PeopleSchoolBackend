package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personauditsnapshot.PersonAuditSnapshot;
import org.springframework.stereotype.Component;

@Component
public class PersonAuditSnapshotMapper {

    public PersonAuditSnapshot toDomain(PersonAuditSnapshotJpaEntity entity) {
        return new PersonAuditSnapshot(
                entity.getId(),
                entity.getAggregateId(),
                entity.getAggregateType(),
                entity.getSnapshotJson(),
                entity.getCapturedAt(),
                entity.getCapturedBy(),
                entity.getPersonId(),
                entity.getTenantId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getLastModifiedAt(),
                entity.getLastModifiedBy(),
                entity.getVersion());
    }
}

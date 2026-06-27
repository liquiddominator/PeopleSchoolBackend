package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.conflict.ConflictStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.conflict.ConflictType;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.conflict.PersonConflict;
import org.springframework.stereotype.Component;

@Component
public class PersonConflictMapper {

    public PersonConflict toDomain(PersonConflictJpaEntity entity) {
        return new PersonConflict(
                entity.getId(),
                ConflictType.valueOf(entity.getConflictType()),
                ConflictStatus.valueOf(entity.getConflictStatus()),
                entity.getPersonAId(),
                entity.getPersonBId(),
                entity.getDescription(),
                entity.getScore(),
                entity.getTenantId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getLastModifiedAt(),
                entity.getLastModifiedBy(),
                entity.getVersion());
    }

    public PersonConflictJpaEntity toNewEntity(PersonConflict domain) {
        PersonConflictJpaEntity entity = new PersonConflictJpaEntity();
        entity.setTenantId(domain.tenantId());
        entity.setConflictType(domain.conflictType().name());
        entity.setConflictStatus(domain.conflictStatus().name());
        entity.setPersonAId(domain.personAId());
        entity.setPersonBId(domain.personBId());
        entity.setDescription(domain.description());
        entity.setScore(domain.score());
        return entity;
    }

    public void updateEntity(PersonConflictJpaEntity entity, PersonConflict domain) {
        entity.setConflictStatus(domain.conflictStatus().name());
    }
}

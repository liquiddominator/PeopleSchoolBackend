package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personevidencerelation.EvidenceStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personevidencerelation.PersonEvidenceRelation;
import org.springframework.stereotype.Component;

@Component
public class PersonEvidenceRelationMapper {

    public PersonEvidenceRelation toDomain(PersonEvidenceRelationJpaEntity entity) {
        return new PersonEvidenceRelation(
                entity.getId(),
                entity.getPersonId(),
                entity.getHouseholdId(),
                entity.getRelatedContextType(),
                entity.getRelatedContextRefId(),
                entity.getEvidenceRoleCode(),
                entity.getAssetId(),
                EvidenceStatus.valueOf(entity.getEvidenceStatus()),
                entity.getTenantId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getLastModifiedAt(),
                entity.getLastModifiedBy(),
                entity.getVersion());
    }

    public PersonEvidenceRelationJpaEntity toNewEntity(PersonEvidenceRelation domain) {
        PersonEvidenceRelationJpaEntity entity = new PersonEvidenceRelationJpaEntity();
        entity.setTenantId(domain.tenantId());
        entity.setPersonId(domain.personId());
        entity.setHouseholdId(domain.householdId());
        entity.setRelatedContextType(domain.relatedContextType());
        entity.setRelatedContextRefId(domain.relatedContextRefId());
        entity.setEvidenceRoleCode(domain.evidenceRoleCode());
        entity.setAssetId(domain.assetId());
        entity.setEvidenceStatus(domain.evidenceStatus().name());
        return entity;
    }
}

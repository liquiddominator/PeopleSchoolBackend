package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.persondocumentreference.DocumentReferenceStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.persondocumentreference.PersonDocumentReference;
import org.springframework.stereotype.Component;

@Component
public class PersonDocumentReferenceMapper {

    public PersonDocumentReference toDomain(PersonDocumentReferenceJpaEntity entity) {
        return new PersonDocumentReference(
                entity.getId(),
                entity.getPersonId(),
                entity.getDocumentReferenceTypeCode(),
                entity.getAssetId(),
                DocumentReferenceStatus.valueOf(entity.getReferenceStatus()),
                entity.getNotes(),
                entity.getTenantId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getLastModifiedAt(),
                entity.getLastModifiedBy(),
                entity.getVersion());
    }

    public PersonDocumentReferenceJpaEntity toNewEntity(PersonDocumentReference domain) {
        PersonDocumentReferenceJpaEntity entity = new PersonDocumentReferenceJpaEntity();
        entity.setTenantId(domain.tenantId());
        entity.setPersonId(domain.personId());
        entity.setDocumentReferenceTypeCode(domain.documentReferenceTypeCode());
        entity.setAssetId(domain.assetId());
        entity.setReferenceStatus(domain.referenceStatus().name());
        entity.setNotes(domain.notes());
        return entity;
    }
}

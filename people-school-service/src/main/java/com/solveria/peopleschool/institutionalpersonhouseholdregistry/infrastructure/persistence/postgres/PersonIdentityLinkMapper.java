package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personidentitylink.IdentityLinkStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personidentitylink.PersonIdentityLink;
import org.springframework.stereotype.Component;

@Component
public class PersonIdentityLinkMapper {

    public PersonIdentityLink toDomain(PersonIdentityLinkJpaEntity entity) {
        return new PersonIdentityLink(
                entity.getId(),
                entity.getPersonId(),
                entity.getIamSubjectId(),
                entity.getIdentityProviderCode(),
                IdentityLinkStatus.valueOf(entity.getIdentityLinkStatus()),
                entity.getLinkedAt(),
                entity.getLinkedBy(),
                entity.getTenantId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getLastModifiedAt(),
                entity.getLastModifiedBy(),
                entity.getVersion());
    }

    public PersonIdentityLinkJpaEntity toNewEntity(PersonIdentityLink domain) {
        PersonIdentityLinkJpaEntity entity = new PersonIdentityLinkJpaEntity();
        entity.setTenantId(domain.tenantId());
        entity.setPersonId(domain.personId());
        entity.setIamSubjectId(domain.iamSubjectId());
        entity.setIdentityProviderCode(domain.identityProviderCode());
        entity.setIdentityLinkStatus(domain.identityLinkStatus().name());
        entity.setLinkedAt(domain.linkedAt());
        entity.setLinkedBy(domain.linkedBy());
        return entity;
    }
}

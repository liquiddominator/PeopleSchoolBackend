package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalaffiliation.AffiliationStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalaffiliation.InstitutionalAffiliation;
import org.springframework.stereotype.Component;

@Component
public class InstitutionalAffiliationMapper {

    public InstitutionalAffiliation toDomain(InstitutionalAffiliationJpaEntity entity) {
        return new InstitutionalAffiliation(
                entity.getId(),
                entity.getPersonId(),
                entity.getAffiliationTypeCode(),
                entity.getSiteId(),
                entity.getOrganizationalUnitId(),
                AffiliationStatus.valueOf(entity.getAffiliationStatus()),
                entity.getEffectiveFrom(),
                entity.getEffectiveTo(),
                entity.getTenantId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getLastModifiedAt(),
                entity.getLastModifiedBy(),
                entity.getVersion());
    }

    public InstitutionalAffiliationJpaEntity toNewEntity(InstitutionalAffiliation domain) {
        InstitutionalAffiliationJpaEntity entity = new InstitutionalAffiliationJpaEntity();
        entity.setTenantId(domain.tenantId());
        entity.setPersonId(domain.personId());
        entity.setAffiliationTypeCode(domain.affiliationTypeCode());
        entity.setSiteId(domain.siteId());
        entity.setOrganizationalUnitId(domain.organizationalUnitId());
        entity.setAffiliationStatus(domain.affiliationStatus().name());
        entity.setEffectiveFrom(domain.effectiveFrom());
        entity.setEffectiveTo(domain.effectiveTo());
        return entity;
    }
}

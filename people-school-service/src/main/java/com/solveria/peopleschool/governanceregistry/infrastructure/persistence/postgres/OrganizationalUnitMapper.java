package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.governanceregistry.domain.orgunit.OrgUnitScopeStatus;
import com.solveria.peopleschool.governanceregistry.domain.orgunit.OrgUnitType;
import com.solveria.peopleschool.governanceregistry.domain.orgunit.OrganizationalUnit;
import org.springframework.stereotype.Component;

@Component
public class OrganizationalUnitMapper {

    public OrganizationalUnit toDomain(OrganizationalUnitJpaEntity e) {
        return new OrganizationalUnit(
                e.getId(),
                e.getEducationUnitId(),
                e.getParentOrgUnitId(),
                e.getOrgUnitCode(),
                e.getOrgUnitName(),
                OrgUnitType.valueOf(e.getOrgUnitType()),
                e.getDisplayOrder(),
                OrgUnitScopeStatus.valueOf(e.getUnitScopeStatus()),
                e.getTenantId(),
                e.getCreatedAt(),
                e.getCreatedBy(),
                e.getLastModifiedAt(),
                e.getLastModifiedBy(),
                e.getVersion());
    }

    public OrganizationalUnitJpaEntity toNewEntity(OrganizationalUnit d) {
        OrganizationalUnitJpaEntity entity = new OrganizationalUnitJpaEntity();
        entity.setTenantId(d.tenantId());
        entity.setEducationUnitId(d.educationUnitId());
        entity.setParentOrgUnitId(d.parentOrgUnitId());
        entity.setOrgUnitCode(d.orgUnitCode());
        entity.setOrgUnitName(d.orgUnitName());
        entity.setOrgUnitType(d.orgUnitType().name());
        entity.setDisplayOrder(d.displayOrder());
        entity.setUnitScopeStatus(d.unitScopeStatus().name());
        return entity;
    }

    public void updateEntity(OrganizationalUnitJpaEntity entity, OrganizationalUnit d) {
        entity.setParentOrgUnitId(d.parentOrgUnitId());
        entity.setOrgUnitName(d.orgUnitName());
        entity.setOrgUnitType(d.orgUnitType().name());
        entity.setDisplayOrder(d.displayOrder());
        entity.setUnitScopeStatus(d.unitScopeStatus().name());
    }
}

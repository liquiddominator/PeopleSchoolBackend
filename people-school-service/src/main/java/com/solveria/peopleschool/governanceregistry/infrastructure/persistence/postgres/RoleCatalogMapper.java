package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.governanceregistry.domain.rolecatalog.InstitutionalRoleCatalog;
import com.solveria.peopleschool.governanceregistry.domain.rolecatalog.RoleCatalogStatus;
import com.solveria.peopleschool.governanceregistry.domain.rolecatalog.RoleFamily;
import org.springframework.stereotype.Component;

@Component
public class RoleCatalogMapper {

    public InstitutionalRoleCatalog toDomain(RoleCatalogJpaEntity e) {
        return new InstitutionalRoleCatalog(
                e.getId(),
                e.getEducationUnitId(),
                e.getRoleCode(),
                e.getRoleName(),
                RoleFamily.valueOf(e.getRoleFamily()),
                e.isAssignableToPerson(),
                RoleCatalogStatus.valueOf(e.getRoleCatalogStatus()),
                e.getDisplayOrder(),
                e.getTenantId(),
                e.getCreatedAt(),
                e.getCreatedBy(),
                e.getLastModifiedAt(),
                e.getLastModifiedBy(),
                e.getVersion());
    }

    public RoleCatalogJpaEntity toNewEntity(InstitutionalRoleCatalog d) {
        RoleCatalogJpaEntity entity = new RoleCatalogJpaEntity();
        entity.setTenantId(d.tenantId());
        entity.setEducationUnitId(d.educationUnitId());
        entity.setRoleCode(d.roleCode());
        entity.setRoleName(d.roleName());
        entity.setRoleFamily(d.roleFamily().name());
        entity.setAssignableToPerson(d.isAssignableToPerson());
        entity.setRoleCatalogStatus(d.roleCatalogStatus().name());
        entity.setDisplayOrder(d.displayOrder());
        return entity;
    }

    public void updateEntity(RoleCatalogJpaEntity entity, InstitutionalRoleCatalog d) {
        entity.setRoleName(d.roleName());
        entity.setRoleFamily(d.roleFamily().name());
        entity.setAssignableToPerson(d.isAssignableToPerson());
        entity.setRoleCatalogStatus(d.roleCatalogStatus().name());
        entity.setDisplayOrder(d.displayOrder());
    }
}

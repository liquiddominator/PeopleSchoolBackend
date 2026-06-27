package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalroleassignment.InstitutionalRoleAssignment;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalroleassignment.RoleAssignmentStatus;
import org.springframework.stereotype.Component;

@Component
public class InstitutionalRoleAssignmentMapper {

    public InstitutionalRoleAssignment toDomain(InstitutionalRoleAssignmentJpaEntity entity) {
        return new InstitutionalRoleAssignment(
                entity.getId(),
                entity.getPersonId(),
                entity.getRoleCode(),
                RoleAssignmentStatus.valueOf(entity.getRoleStatus()),
                entity.getEffectiveFrom(),
                entity.getEffectiveTo(),
                entity.getTenantId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getLastModifiedAt(),
                entity.getLastModifiedBy(),
                entity.getVersion());
    }

    public InstitutionalRoleAssignmentJpaEntity toNewEntity(InstitutionalRoleAssignment domain) {
        InstitutionalRoleAssignmentJpaEntity entity = new InstitutionalRoleAssignmentJpaEntity();
        entity.setTenantId(domain.tenantId());
        entity.setPersonId(domain.personId());
        entity.setRoleCode(domain.roleCode());
        entity.setRoleStatus(domain.roleStatus().name());
        entity.setEffectiveFrom(domain.effectiveFrom());
        entity.setEffectiveTo(domain.effectiveTo());
        return entity;
    }
}

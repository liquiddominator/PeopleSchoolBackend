package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.pickupauthorization.AuthorizationStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.pickupauthorization.PickupAuthorization;
import org.springframework.stereotype.Component;

@Component
public class PickupAuthorizationMapper {

    public PickupAuthorization toDomain(PickupAuthorizationJpaEntity entity) {
        return new PickupAuthorization(
                entity.getId(),
                entity.getStudentPersonId(),
                entity.getAuthorizedPersonId(),
                AuthorizationStatus.valueOf(entity.getAuthorizationStatus()),
                entity.getEffectiveFrom(),
                entity.getEffectiveTo(),
                entity.getAuthorizationScopeCode(),
                entity.getNotes(),
                entity.getTenantId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getLastModifiedAt(),
                entity.getLastModifiedBy(),
                entity.getVersion());
    }

    public PickupAuthorizationJpaEntity toNewEntity(PickupAuthorization domain) {
        PickupAuthorizationJpaEntity entity = new PickupAuthorizationJpaEntity();
        entity.setTenantId(domain.tenantId());
        entity.setStudentPersonId(domain.studentPersonId());
        entity.setAuthorizedPersonId(domain.authorizedPersonId());
        entity.setAuthorizationStatus(domain.authorizationStatus().name());
        entity.setEffectiveFrom(domain.effectiveFrom());
        entity.setEffectiveTo(domain.effectiveTo());
        entity.setAuthorizationScopeCode(domain.authorizationScopeCode());
        entity.setNotes(domain.notes());
        return entity;
    }
}

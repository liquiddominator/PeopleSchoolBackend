package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.Household;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.HouseholdStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.HouseholdTypeCode;
import org.springframework.stereotype.Component;

@Component
public class HouseholdMapper {

    public Household toDomain(HouseholdJpaEntity entity) {
        return new Household(
                entity.getId(),
                entity.getHouseholdCode(),
                entity.getHouseholdName(),
                HouseholdTypeCode.valueOf(entity.getHouseholdTypeCode()),
                HouseholdStatus.valueOf(entity.getHouseholdStatus()),
                entity.getPrimaryBillingAddressId(),
                entity.getTenantId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getLastModifiedAt(),
                entity.getLastModifiedBy(),
                entity.getVersion());
    }

    public HouseholdJpaEntity toNewEntity(Household domain) {
        HouseholdJpaEntity entity = new HouseholdJpaEntity();
        entity.setTenantId(domain.tenantId());
        entity.setHouseholdCode(domain.householdCode());
        entity.setHouseholdName(domain.householdName());
        entity.setHouseholdTypeCode(domain.householdTypeCode().name());
        entity.setHouseholdStatus(domain.householdStatus().name());
        entity.setPrimaryBillingAddressId(domain.primaryBillingAddressId());
        return entity;
    }

    public void updateEntity(HouseholdJpaEntity entity, Household domain) {
        entity.setHouseholdName(domain.householdName());
        entity.setHouseholdTypeCode(domain.householdTypeCode().name());
        entity.setHouseholdStatus(domain.householdStatus().name());
        entity.setPrimaryBillingAddressId(domain.primaryBillingAddressId());
    }
}

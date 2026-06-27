package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.householdmembership.HouseholdMembership;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.householdmembership.MembershipRoleCode;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.householdmembership.MembershipStatus;
import org.springframework.stereotype.Component;

@Component
public class HouseholdMembershipMapper {

    public HouseholdMembership toDomain(HouseholdMembershipJpaEntity entity) {
        return new HouseholdMembership(
                entity.getId(),
                entity.getHouseholdId(),
                entity.getPersonId(),
                MembershipRoleCode.valueOf(entity.getMembershipRoleCode()),
                MembershipStatus.valueOf(entity.getMembershipStatus()),
                entity.getEffectiveFrom(),
                entity.getEffectiveTo(),
                entity.isPrimaryGuardianGroup(),
                entity.getTenantId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getLastModifiedAt(),
                entity.getLastModifiedBy(),
                entity.getVersion());
    }

    public HouseholdMembershipJpaEntity toNewEntity(HouseholdMembership domain) {
        HouseholdMembershipJpaEntity entity = new HouseholdMembershipJpaEntity();
        entity.setTenantId(domain.tenantId());
        entity.setHouseholdId(domain.householdId());
        entity.setPersonId(domain.personId());
        entity.setMembershipRoleCode(domain.membershipRoleCode().name());
        entity.setMembershipStatus(domain.membershipStatus().name());
        entity.setEffectiveFrom(domain.effectiveFrom());
        entity.setEffectiveTo(domain.effectiveTo());
        entity.setPrimaryGuardianGroup(domain.isPrimaryGuardianGroup());
        return entity;
    }

    public void updateEntity(HouseholdMembershipJpaEntity entity, HouseholdMembership domain) {
        entity.setMembershipRoleCode(domain.membershipRoleCode().name());
        entity.setMembershipStatus(domain.membershipStatus().name());
        entity.setEffectiveFrom(domain.effectiveFrom());
        entity.setEffectiveTo(domain.effectiveTo());
        entity.setPrimaryGuardianGroup(domain.isPrimaryGuardianGroup());
    }
}

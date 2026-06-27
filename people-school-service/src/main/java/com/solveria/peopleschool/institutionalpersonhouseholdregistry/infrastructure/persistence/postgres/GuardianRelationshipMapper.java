package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.guardianrelationship.GuardianAuthorityStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.guardianrelationship.GuardianRelationship;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.guardianrelationship.GuardianTypeCode;
import org.springframework.stereotype.Component;

@Component
public class GuardianRelationshipMapper {

    public GuardianRelationship toDomain(GuardianRelationshipJpaEntity entity) {
        return new GuardianRelationship(
                entity.getId(),
                entity.getStudentPersonId(),
                entity.getGuardianPersonId(),
                entity.getHouseholdId(),
                GuardianTypeCode.valueOf(entity.getGuardianTypeCode()),
                GuardianAuthorityStatus.valueOf(entity.getLegalAuthorityStatus()),
                GuardianAuthorityStatus.valueOf(entity.getSchoolAuthorityStatus()),
                entity.getEffectiveFrom(),
                entity.getEffectiveTo(),
                entity.getNotes(),
                entity.getTenantId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getLastModifiedAt(),
                entity.getLastModifiedBy(),
                entity.getVersion());
    }

    public GuardianRelationshipJpaEntity toNewEntity(GuardianRelationship domain) {
        GuardianRelationshipJpaEntity entity = new GuardianRelationshipJpaEntity();
        entity.setTenantId(domain.tenantId());
        entity.setStudentPersonId(domain.studentPersonId());
        entity.setGuardianPersonId(domain.guardianPersonId());
        entity.setHouseholdId(domain.householdId());
        entity.setGuardianTypeCode(domain.guardianTypeCode().name());
        entity.setLegalAuthorityStatus(domain.legalAuthorityStatus().name());
        entity.setSchoolAuthorityStatus(domain.schoolAuthorityStatus().name());
        entity.setEffectiveFrom(domain.effectiveFrom());
        entity.setEffectiveTo(domain.effectiveTo());
        entity.setNotes(domain.notes());
        return entity;
    }
}

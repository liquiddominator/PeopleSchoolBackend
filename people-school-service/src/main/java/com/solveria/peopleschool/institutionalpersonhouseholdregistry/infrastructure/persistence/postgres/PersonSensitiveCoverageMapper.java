package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitivecoverage.CoverageStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitivecoverage.PersonSensitiveCoverage;
import org.springframework.stereotype.Component;

@Component
public class PersonSensitiveCoverageMapper {

    public PersonSensitiveCoverage toDomain(PersonSensitiveCoverageJpaEntity entity) {
        return new PersonSensitiveCoverage(
                entity.getId(),
                entity.getPersonSensitiveProfileId(),
                entity.getPersonId(),
                entity.getCoverageTypeCode(),
                entity.getProviderName(),
                entity.getPolicyNumber(),
                CoverageStatus.valueOf(entity.getCoverageStatus()),
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

    public PersonSensitiveCoverageJpaEntity toNewEntity(PersonSensitiveCoverage domain) {
        PersonSensitiveCoverageJpaEntity entity = new PersonSensitiveCoverageJpaEntity();
        entity.setTenantId(domain.tenantId());
        entity.setPersonSensitiveProfileId(domain.personSensitiveProfileId());
        entity.setPersonId(domain.personId());
        entity.setCoverageTypeCode(domain.coverageTypeCode());
        entity.setProviderName(domain.providerName());
        entity.setPolicyNumber(domain.policyNumber());
        entity.setCoverageStatus(domain.coverageStatus().name());
        entity.setEffectiveFrom(domain.effectiveFrom());
        entity.setEffectiveTo(domain.effectiveTo());
        entity.setNotes(domain.notes());
        return entity;
    }
}

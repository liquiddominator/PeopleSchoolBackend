package com.solveria.peopleschool.schoolregulationsregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.schoolregulationsregistry.domain.schoolregulation.CriticalityLevel;
import com.solveria.peopleschool.schoolregulationsregistry.domain.schoolregulation.RegulationStatus;
import com.solveria.peopleschool.schoolregulationsregistry.domain.schoolregulation.RegulationType;
import com.solveria.peopleschool.schoolregulationsregistry.domain.schoolregulation.SchoolRegulation;
import org.springframework.stereotype.Component;

@Component
public class SchoolRegulationMapper {

    public SchoolRegulation toDomain(SchoolRegulationJpaEntity e) {
        return new SchoolRegulation(
                e.getId(),
                e.getEducationUnitId(),
                e.getRegulationCode(),
                e.getTitle(),
                e.getDescription(),
                RegulationType.valueOf(e.getRegulationTypeCode()),
                e.getIssuingAuthorityTypeCode(),
                CriticalityLevel.valueOf(e.getCriticalityLevel()),
                RegulationStatus.valueOf(e.getRegulationStatus()),
                e.getTenantId(),
                e.getCreatedAt(),
                e.getCreatedBy(),
                e.getLastModifiedAt(),
                e.getLastModifiedBy(),
                e.getVersion());
    }

    public SchoolRegulationJpaEntity toNewEntity(SchoolRegulation d) {
        SchoolRegulationJpaEntity entity = new SchoolRegulationJpaEntity();
        entity.setTenantId(d.tenantId());
        entity.setEducationUnitId(d.educationUnitId());
        entity.setRegulationCode(d.regulationCode());
        entity.setTitle(d.title());
        entity.setDescription(d.description());
        entity.setRegulationTypeCode(d.regulationType().name());
        entity.setIssuingAuthorityTypeCode(d.issuingAuthorityTypeCode());
        entity.setCriticalityLevel(d.criticalityLevel().name());
        entity.setRegulationStatus(d.regulationStatus().name());
        return entity;
    }

    public void updateEntity(SchoolRegulationJpaEntity entity, SchoolRegulation d) {
        entity.setTitle(d.title());
        entity.setDescription(d.description());
        entity.setRegulationTypeCode(d.regulationType().name());
        entity.setIssuingAuthorityTypeCode(d.issuingAuthorityTypeCode());
        entity.setCriticalityLevel(d.criticalityLevel().name());
        entity.setRegulationStatus(d.regulationStatus().name());
    }
}

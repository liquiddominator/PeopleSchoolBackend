package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.governanceregistry.domain.educationlevel.EducationLevel;
import com.solveria.peopleschool.governanceregistry.domain.educationlevel.LevelStatus;
import org.springframework.stereotype.Component;

@Component
public class EducationLevelMapper {

    public EducationLevel toDomain(EducationLevelJpaEntity e) {
        return new EducationLevel(
                e.getId(),
                e.getEducationUnitId(),
                e.getLevelCode(),
                e.getLevelName(),
                e.getOfficialReferenceCode(),
                e.getLevelSequence(),
                LevelStatus.valueOf(e.getLevelStatus()),
                e.getTenantId(),
                e.getCreatedAt(),
                e.getCreatedBy(),
                e.getLastModifiedAt(),
                e.getLastModifiedBy(),
                e.getVersion());
    }

    public EducationLevelJpaEntity toNewEntity(EducationLevel d) {
        EducationLevelJpaEntity entity = new EducationLevelJpaEntity();
        entity.setTenantId(d.tenantId());
        entity.setEducationUnitId(d.educationUnitId());
        entity.setLevelCode(d.levelCode());
        entity.setLevelName(d.levelName());
        entity.setOfficialReferenceCode(d.officialReferenceCode());
        entity.setLevelSequence(d.levelSequence());
        entity.setLevelStatus(d.levelStatus().name());
        return entity;
    }

    public void updateEntity(EducationLevelJpaEntity entity, EducationLevel d) {
        entity.setLevelName(d.levelName());
        entity.setOfficialReferenceCode(d.officialReferenceCode());
        entity.setLevelSequence(d.levelSequence());
        entity.setLevelStatus(d.levelStatus().name());
    }
}

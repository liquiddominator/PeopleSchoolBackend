package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.governanceregistry.domain.schoolyear.SchoolYear;
import com.solveria.peopleschool.governanceregistry.domain.schoolyear.SchoolYearLifecycleStatus;
import org.springframework.stereotype.Component;

@Component
public class SchoolYearMapper {

    public SchoolYear toDomain(SchoolYearJpaEntity e) {
        return new SchoolYear(
                e.getId(),
                e.getEducationUnitId(),
                e.getSchoolYearCode(),
                e.getSchoolYearName(),
                e.getStartDate(),
                e.getEndDate(),
                SchoolYearLifecycleStatus.valueOf(e.getLifecycleStatus()),
                e.isCurrentDefault(),
                e.isVisibleForQuery(),
                e.isEditable(),
                e.isReportable(),
                e.isArchived(),
                e.getContextPriority(),
                e.getTenantId(),
                e.getCreatedAt(),
                e.getCreatedBy(),
                e.getLastModifiedAt(),
                e.getLastModifiedBy(),
                e.getVersion());
    }

    public SchoolYearJpaEntity toNewEntity(SchoolYear d) {
        SchoolYearJpaEntity entity = new SchoolYearJpaEntity();
        entity.setTenantId(d.tenantId());
        entity.setEducationUnitId(d.educationUnitId());
        entity.setSchoolYearCode(d.schoolYearCode());
        entity.setSchoolYearName(d.schoolYearName());
        entity.setStartDate(d.startDate());
        entity.setEndDate(d.endDate());
        entity.setLifecycleStatus(d.lifecycleStatus().name());
        entity.setCurrentDefault(d.isCurrentDefault());
        entity.setVisibleForQuery(d.isVisibleForQuery());
        entity.setEditable(d.isEditable());
        entity.setReportable(d.isReportable());
        entity.setArchived(d.isArchived());
        entity.setContextPriority(d.contextPriority());
        return entity;
    }

    public void updateEntity(SchoolYearJpaEntity entity, SchoolYear d) {
        entity.setSchoolYearName(d.schoolYearName());
        entity.setStartDate(d.startDate());
        entity.setEndDate(d.endDate());
        entity.setLifecycleStatus(d.lifecycleStatus().name());
        entity.setCurrentDefault(d.isCurrentDefault());
        entity.setVisibleForQuery(d.isVisibleForQuery());
        entity.setEditable(d.isEditable());
        entity.setReportable(d.isReportable());
        entity.setArchived(d.isArchived());
        entity.setContextPriority(d.contextPriority());
    }
}

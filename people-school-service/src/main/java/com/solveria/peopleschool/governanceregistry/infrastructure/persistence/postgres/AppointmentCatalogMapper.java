package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.governanceregistry.domain.appointmentcatalog.AppointmentCatalog;
import com.solveria.peopleschool.governanceregistry.domain.appointmentcatalog.AppointmentCatalogStatus;
import org.springframework.stereotype.Component;

@Component
public class AppointmentCatalogMapper {

    public AppointmentCatalog toDomain(AppointmentCatalogJpaEntity e) {
        return new AppointmentCatalog(
                e.getId(),
                e.getEducationUnitId(),
                e.getAppointmentCode(),
                e.getAppointmentName(),
                e.getOrgUnitTypeScope(),
                e.isRequiresDocumentSupport(),
                AppointmentCatalogStatus.valueOf(e.getAppointmentCatalogStatus()),
                e.getDisplayOrder(),
                e.getTenantId(),
                e.getCreatedAt(),
                e.getCreatedBy(),
                e.getLastModifiedAt(),
                e.getLastModifiedBy(),
                e.getVersion());
    }

    public AppointmentCatalogJpaEntity toNewEntity(AppointmentCatalog d) {
        AppointmentCatalogJpaEntity entity = new AppointmentCatalogJpaEntity();
        entity.setTenantId(d.tenantId());
        entity.setEducationUnitId(d.educationUnitId());
        entity.setAppointmentCode(d.appointmentCode());
        entity.setAppointmentName(d.appointmentName());
        entity.setOrgUnitTypeScope(d.orgUnitTypeScope());
        entity.setRequiresDocumentSupport(d.requiresDocumentSupport());
        entity.setAppointmentCatalogStatus(d.appointmentCatalogStatus().name());
        entity.setDisplayOrder(d.displayOrder());
        return entity;
    }

    public void updateEntity(AppointmentCatalogJpaEntity entity, AppointmentCatalog d) {
        entity.setAppointmentName(d.appointmentName());
        entity.setOrgUnitTypeScope(d.orgUnitTypeScope());
        entity.setRequiresDocumentSupport(d.requiresDocumentSupport());
        entity.setAppointmentCatalogStatus(d.appointmentCatalogStatus().name());
        entity.setDisplayOrder(d.displayOrder());
    }
}

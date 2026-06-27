package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.schoolorganizationalappointment.AppointmentAssignmentStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.schoolorganizationalappointment.SchoolOrganizationalAppointment;
import org.springframework.stereotype.Component;

@Component
public class SchoolOrganizationalAppointmentMapper {

    public SchoolOrganizationalAppointment toDomain(
            SchoolOrganizationalAppointmentJpaEntity entity) {
        return new SchoolOrganizationalAppointment(
                entity.getId(),
                entity.getPersonId(),
                entity.getAppointmentCode(),
                entity.getOrganizationalUnitId(),
                AppointmentAssignmentStatus.valueOf(entity.getAppointmentStatus()),
                entity.getStartedAt(),
                entity.getEndedAt(),
                entity.getTenantId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getLastModifiedAt(),
                entity.getLastModifiedBy(),
                entity.getVersion());
    }

    public SchoolOrganizationalAppointmentJpaEntity toNewEntity(
            SchoolOrganizationalAppointment domain) {
        SchoolOrganizationalAppointmentJpaEntity entity =
                new SchoolOrganizationalAppointmentJpaEntity();
        entity.setTenantId(domain.tenantId());
        entity.setPersonId(domain.personId());
        entity.setAppointmentCode(domain.appointmentCode());
        entity.setOrganizationalUnitId(domain.organizationalUnitId());
        entity.setAppointmentStatus(domain.appointmentStatus().name());
        entity.setStartedAt(domain.startedAt());
        entity.setEndedAt(domain.endedAt());
        return entity;
    }
}

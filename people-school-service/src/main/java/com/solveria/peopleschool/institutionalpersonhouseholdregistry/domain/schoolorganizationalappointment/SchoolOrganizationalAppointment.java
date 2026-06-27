package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.schoolorganizationalappointment;

import java.time.LocalDateTime;

public record SchoolOrganizationalAppointment(
        Long id,
        Long personId,
        String appointmentCode,
        Long organizationalUnitId,
        AppointmentAssignmentStatus appointmentStatus,
        LocalDateTime startedAt,
        LocalDateTime endedAt,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

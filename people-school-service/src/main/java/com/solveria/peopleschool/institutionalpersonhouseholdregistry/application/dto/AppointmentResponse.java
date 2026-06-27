package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.schoolorganizationalappointment.AppointmentAssignmentStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.schoolorganizationalappointment.SchoolOrganizationalAppointment;
import java.time.LocalDateTime;

public record AppointmentResponse(
        Long id,
        Long personId,
        String appointmentCode,
        Long organizationalUnitId,
        AppointmentAssignmentStatus appointmentStatus,
        LocalDateTime startedAt,
        LocalDateTime endedAt) {

    public static AppointmentResponse from(SchoolOrganizationalAppointment a) {
        return new AppointmentResponse(
                a.id(),
                a.personId(),
                a.appointmentCode(),
                a.organizationalUnitId(),
                a.appointmentStatus(),
                a.startedAt(),
                a.endedAt());
    }
}

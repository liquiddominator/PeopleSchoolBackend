package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.schoolorganizationalappointment.AppointmentAssignmentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record CreateAppointmentRequest(
        @NotNull Long personId,
        @NotBlank @Size(max = 50) String appointmentCode,
        Long organizationalUnitId,
        @NotNull AppointmentAssignmentStatus appointmentStatus,
        @NotNull LocalDateTime startedAt,
        LocalDateTime endedAt) {}

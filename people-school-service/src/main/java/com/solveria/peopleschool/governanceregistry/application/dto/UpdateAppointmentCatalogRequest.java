package com.solveria.peopleschool.governanceregistry.application.dto;

import com.solveria.peopleschool.governanceregistry.domain.appointmentcatalog.AppointmentCatalogStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateAppointmentCatalogRequest(
        @NotBlank @Size(max = 180) String appointmentName,
        @Size(max = 60) String orgUnitTypeScope,
        boolean requiresDocumentSupport,
        @NotNull AppointmentCatalogStatus appointmentCatalogStatus,
        @Min(0) @Max(999) int displayOrder) {}

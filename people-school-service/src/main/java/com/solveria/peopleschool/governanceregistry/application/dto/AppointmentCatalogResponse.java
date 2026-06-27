package com.solveria.peopleschool.governanceregistry.application.dto;

import com.solveria.peopleschool.governanceregistry.domain.appointmentcatalog.AppointmentCatalog;
import com.solveria.peopleschool.governanceregistry.domain.appointmentcatalog.AppointmentCatalogStatus;
import java.time.LocalDateTime;

public record AppointmentCatalogResponse(
        Long id,
        Long educationUnitId,
        String appointmentCode,
        String appointmentName,
        String orgUnitTypeScope,
        boolean requiresDocumentSupport,
        AppointmentCatalogStatus appointmentCatalogStatus,
        int displayOrder,
        String tenantId,
        LocalDateTime createdAt,
        LocalDateTime lastModifiedAt) {

    public static AppointmentCatalogResponse from(AppointmentCatalog a) {
        return new AppointmentCatalogResponse(
                a.id(),
                a.educationUnitId(),
                a.appointmentCode(),
                a.appointmentName(),
                a.orgUnitTypeScope(),
                a.requiresDocumentSupport(),
                a.appointmentCatalogStatus(),
                a.displayOrder(),
                a.tenantId(),
                a.createdAt(),
                a.lastModifiedAt());
    }
}

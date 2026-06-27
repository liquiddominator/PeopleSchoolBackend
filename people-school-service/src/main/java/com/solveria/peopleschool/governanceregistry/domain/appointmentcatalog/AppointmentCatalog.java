package com.solveria.peopleschool.governanceregistry.domain.appointmentcatalog;

import java.time.LocalDateTime;

public record AppointmentCatalog(
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
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

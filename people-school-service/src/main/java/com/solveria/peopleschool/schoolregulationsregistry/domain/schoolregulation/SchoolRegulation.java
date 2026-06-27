package com.solveria.peopleschool.schoolregulationsregistry.domain.schoolregulation;

import java.time.LocalDateTime;

public record SchoolRegulation(
        Long id,
        Long educationUnitId,
        String regulationCode,
        String title,
        String description,
        RegulationType regulationType,
        String issuingAuthorityTypeCode,
        CriticalityLevel criticalityLevel,
        RegulationStatus regulationStatus,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

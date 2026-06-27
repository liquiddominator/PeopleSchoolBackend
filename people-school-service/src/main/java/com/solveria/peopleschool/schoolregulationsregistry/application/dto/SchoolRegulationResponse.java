package com.solveria.peopleschool.schoolregulationsregistry.application.dto;

import com.solveria.peopleschool.schoolregulationsregistry.domain.schoolregulation.CriticalityLevel;
import com.solveria.peopleschool.schoolregulationsregistry.domain.schoolregulation.RegulationStatus;
import com.solveria.peopleschool.schoolregulationsregistry.domain.schoolregulation.RegulationType;
import com.solveria.peopleschool.schoolregulationsregistry.domain.schoolregulation.SchoolRegulation;
import java.time.LocalDateTime;

public record SchoolRegulationResponse(
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
        LocalDateTime lastModifiedAt) {

    public static SchoolRegulationResponse from(SchoolRegulation r) {
        return new SchoolRegulationResponse(
                r.id(),
                r.educationUnitId(),
                r.regulationCode(),
                r.title(),
                r.description(),
                r.regulationType(),
                r.issuingAuthorityTypeCode(),
                r.criticalityLevel(),
                r.regulationStatus(),
                r.tenantId(),
                r.createdAt(),
                r.lastModifiedAt());
    }
}

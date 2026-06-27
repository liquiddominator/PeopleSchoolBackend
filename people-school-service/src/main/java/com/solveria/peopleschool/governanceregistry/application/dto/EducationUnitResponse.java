package com.solveria.peopleschool.governanceregistry.application.dto;

import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnit;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.UnitStatus;
import java.time.LocalDateTime;

public record EducationUnitResponse(
        Long id,
        String unitCode,
        String officialName,
        String shortName,
        String ministryRueCode,
        String administrativeDependency,
        String institutionalType,
        String serviceType,
        String phoneNumber,
        String emailAddress,
        String addressLine,
        UnitStatus unitStatus,
        String tenantId,
        LocalDateTime createdAt,
        LocalDateTime lastModifiedAt) {

    public static EducationUnitResponse from(EducationUnit e) {
        return new EducationUnitResponse(
                e.id(),
                e.unitCode(),
                e.officialName(),
                e.shortName(),
                e.ministryRueCode(),
                e.administrativeDependency(),
                e.institutionalType(),
                e.serviceType(),
                e.phoneNumber(),
                e.emailAddress(),
                e.addressLine(),
                e.unitStatus(),
                e.tenantId(),
                e.createdAt(),
                e.lastModifiedAt());
    }
}

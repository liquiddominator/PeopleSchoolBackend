package com.solveria.peopleschool.governanceregistry.domain.educationunit;

import java.time.LocalDateTime;

public record EducationUnit(
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
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

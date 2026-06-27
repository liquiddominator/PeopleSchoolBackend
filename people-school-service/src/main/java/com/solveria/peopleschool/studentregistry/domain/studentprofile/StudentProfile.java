package com.solveria.peopleschool.studentregistry.domain.studentprofile;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record StudentProfile(
        Long id,
        Long personId,
        String studentCode,
        StudentStatus studentStatus,
        LocalDate schoolEntryDate,
        Long firstSchoolYearId,
        String currentOperationalStatus,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

package com.solveria.peopleschool.governanceregistry.domain.schoolyear;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record SchoolYear(
        Long id,
        Long educationUnitId,
        String schoolYearCode,
        String schoolYearName,
        LocalDate startDate,
        LocalDate endDate,
        SchoolYearLifecycleStatus lifecycleStatus,
        boolean isCurrentDefault,
        boolean isVisibleForQuery,
        boolean isEditable,
        boolean isReportable,
        boolean isArchived,
        int contextPriority,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

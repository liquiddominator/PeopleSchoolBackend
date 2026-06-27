package com.solveria.peopleschool.governanceregistry.application.dto;

import com.solveria.peopleschool.governanceregistry.domain.schoolyear.SchoolYear;
import com.solveria.peopleschool.governanceregistry.domain.schoolyear.SchoolYearLifecycleStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record SchoolYearResponse(
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
        LocalDateTime lastModifiedAt) {

    public static SchoolYearResponse from(SchoolYear sy) {
        return new SchoolYearResponse(
                sy.id(),
                sy.educationUnitId(),
                sy.schoolYearCode(),
                sy.schoolYearName(),
                sy.startDate(),
                sy.endDate(),
                sy.lifecycleStatus(),
                sy.isCurrentDefault(),
                sy.isVisibleForQuery(),
                sy.isEditable(),
                sy.isReportable(),
                sy.isArchived(),
                sy.contextPriority(),
                sy.tenantId(),
                sy.createdAt(),
                sy.lastModifiedAt());
    }
}

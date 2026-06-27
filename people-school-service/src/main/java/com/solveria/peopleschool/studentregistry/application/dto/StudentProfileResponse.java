package com.solveria.peopleschool.studentregistry.application.dto;

import com.solveria.peopleschool.studentregistry.domain.studentprofile.StudentProfile;
import com.solveria.peopleschool.studentregistry.domain.studentprofile.StudentStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record StudentProfileResponse(
        Long id,
        Long personId,
        String studentCode,
        StudentStatus studentStatus,
        LocalDate schoolEntryDate,
        Long firstSchoolYearId,
        String currentOperationalStatus,
        String tenantId,
        LocalDateTime createdAt,
        LocalDateTime lastModifiedAt) {

    public static StudentProfileResponse from(StudentProfile p) {
        return new StudentProfileResponse(
                p.id(),
                p.personId(),
                p.studentCode(),
                p.studentStatus(),
                p.schoolEntryDate(),
                p.firstSchoolYearId(),
                p.currentOperationalStatus(),
                p.tenantId(),
                p.createdAt(),
                p.lastModifiedAt());
    }
}

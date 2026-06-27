package com.solveria.peopleschool.studentregistry.application.dto;

import com.solveria.peopleschool.studentregistry.domain.studentprofile.StudentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record CreateStudentProfileRequest(
        @NotNull Long personId,
        @NotBlank @Size(max = 80) String studentCode,
        @NotNull StudentStatus studentStatus,
        LocalDate schoolEntryDate,
        Long firstSchoolYearId) {}

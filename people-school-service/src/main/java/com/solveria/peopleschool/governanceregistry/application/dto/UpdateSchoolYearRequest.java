package com.solveria.peopleschool.governanceregistry.application.dto;

import com.solveria.peopleschool.governanceregistry.domain.schoolyear.SchoolYearLifecycleStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record UpdateSchoolYearRequest(
        @NotBlank @Size(max = 120) String schoolYearName,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        @NotNull SchoolYearLifecycleStatus lifecycleStatus) {}

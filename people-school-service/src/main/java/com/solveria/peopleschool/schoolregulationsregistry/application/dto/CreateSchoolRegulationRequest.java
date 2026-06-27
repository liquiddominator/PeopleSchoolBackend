package com.solveria.peopleschool.schoolregulationsregistry.application.dto;

import com.solveria.peopleschool.schoolregulationsregistry.domain.schoolregulation.CriticalityLevel;
import com.solveria.peopleschool.schoolregulationsregistry.domain.schoolregulation.RegulationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateSchoolRegulationRequest(
        @NotBlank @Size(max = 100) String regulationCode,
        @NotBlank @Size(max = 255) String title,
        @Size(max = 2000) String description,
        @NotNull RegulationType regulationType,
        @Size(max = 50) String issuingAuthorityTypeCode,
        @NotNull CriticalityLevel criticalityLevel) {}

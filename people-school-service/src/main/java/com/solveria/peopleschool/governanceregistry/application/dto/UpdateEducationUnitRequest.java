package com.solveria.peopleschool.governanceregistry.application.dto;

import com.solveria.peopleschool.governanceregistry.domain.educationunit.UnitStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateEducationUnitRequest(
        @NotBlank @Size(max = 50) String unitCode,
        @NotBlank @Size(max = 220) String officialName,
        @Size(max = 120) String shortName,
        @Size(max = 80) String ministryRueCode,
        @Size(max = 40) String administrativeDependency,
        @Size(max = 40) String institutionalType,
        @Size(max = 40) String serviceType,
        @Size(max = 50) String phoneNumber,
        @Size(max = 150) String emailAddress,
        @Size(max = 250) String addressLine,
        @NotNull UnitStatus unitStatus) {}

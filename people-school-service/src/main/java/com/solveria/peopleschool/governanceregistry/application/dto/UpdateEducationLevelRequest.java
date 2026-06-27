package com.solveria.peopleschool.governanceregistry.application.dto;

import com.solveria.peopleschool.governanceregistry.domain.educationlevel.LevelStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateEducationLevelRequest(
        @NotBlank @Size(max = 120) String levelName,
        @Size(max = 60) String officialReferenceCode,
        @Min(1) @Max(99) int levelSequence,
        @NotNull LevelStatus levelStatus) {}

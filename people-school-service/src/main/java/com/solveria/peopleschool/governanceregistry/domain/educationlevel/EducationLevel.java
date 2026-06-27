package com.solveria.peopleschool.governanceregistry.domain.educationlevel;

import java.time.LocalDateTime;

public record EducationLevel(
        Long id,
        Long educationUnitId,
        String levelCode,
        String levelName,
        String officialReferenceCode,
        int levelSequence,
        LevelStatus levelStatus,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

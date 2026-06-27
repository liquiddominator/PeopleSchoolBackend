package com.solveria.peopleschool.governanceregistry.application.dto;

import com.solveria.peopleschool.governanceregistry.domain.educationlevel.EducationLevel;
import com.solveria.peopleschool.governanceregistry.domain.educationlevel.LevelStatus;
import java.time.LocalDateTime;

public record EducationLevelResponse(
        Long id,
        Long educationUnitId,
        String levelCode,
        String levelName,
        String officialReferenceCode,
        int levelSequence,
        LevelStatus levelStatus,
        String tenantId,
        LocalDateTime createdAt,
        LocalDateTime lastModifiedAt) {

    public static EducationLevelResponse from(EducationLevel el) {
        return new EducationLevelResponse(
                el.id(),
                el.educationUnitId(),
                el.levelCode(),
                el.levelName(),
                el.officialReferenceCode(),
                el.levelSequence(),
                el.levelStatus(),
                el.tenantId(),
                el.createdAt(),
                el.lastModifiedAt());
    }
}

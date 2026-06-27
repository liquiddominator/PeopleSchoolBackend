package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.conflict.PersonConflict;
import java.time.LocalDateTime;

public record PersonConflictDetailResponse(
        Long id,
        Long leftPersonId,
        Long rightPersonId,
        String conflictTypeCode,
        String conflictStatus,
        String conflictNotes,
        LocalDateTime detectedAt,
        String detectedBy,
        LocalDateTime resolvedAt,
        String resolvedBy) {

    public static PersonConflictDetailResponse from(PersonConflict conflict) {
        boolean resolved =
                "RESUELTO".equals(conflict.conflictStatus().name())
                        || "DESCARTADO".equals(conflict.conflictStatus().name());
        return new PersonConflictDetailResponse(
                conflict.id(),
                conflict.personAId(),
                conflict.personBId(),
                conflict.conflictType().name(),
                conflict.conflictStatus().name(),
                conflict.description(),
                conflict.createdAt(),
                conflict.createdBy(),
                resolved ? conflict.lastModifiedAt() : null,
                resolved ? conflict.lastModifiedBy() : null);
    }
}

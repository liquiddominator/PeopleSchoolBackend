package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.conflict;

import java.time.LocalDateTime;

public record PersonConflict(
        Long id,
        ConflictType conflictType,
        ConflictStatus conflictStatus,
        Long personAId,
        Long personBId,
        String description,
        Integer score,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

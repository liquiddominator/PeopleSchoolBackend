package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personauditsnapshot;

import java.time.LocalDateTime;

public record PersonAuditSnapshot(
        Long id,
        Long aggregateId,
        String aggregateType,
        String snapshotJson,
        LocalDateTime capturedAt,
        String capturedBy,
        Long personId,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

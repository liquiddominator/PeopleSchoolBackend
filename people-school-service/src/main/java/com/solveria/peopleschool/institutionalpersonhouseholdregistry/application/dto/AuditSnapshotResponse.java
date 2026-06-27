package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personauditsnapshot.PersonAuditSnapshot;
import java.time.LocalDateTime;

public record AuditSnapshotResponse(
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
        Long version) {

    public static AuditSnapshotResponse from(PersonAuditSnapshot s) {
        return new AuditSnapshotResponse(
                s.id(),
                s.aggregateId(),
                s.aggregateType(),
                s.snapshotJson(),
                s.capturedAt(),
                s.capturedBy(),
                s.personId(),
                s.tenantId(),
                s.createdAt(),
                s.createdBy(),
                s.lastModifiedAt(),
                s.lastModifiedBy(),
                s.version());
    }
}

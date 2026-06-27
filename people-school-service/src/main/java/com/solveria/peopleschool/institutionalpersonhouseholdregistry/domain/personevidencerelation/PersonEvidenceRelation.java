package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personevidencerelation;

import java.time.LocalDateTime;

public record PersonEvidenceRelation(
        Long id,
        Long personId,
        Long householdId,
        String relatedContextType,
        String relatedContextRefId,
        String evidenceRoleCode,
        Long assetId,
        EvidenceStatus evidenceStatus,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

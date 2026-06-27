package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person;

import java.time.LocalDateTime;

public record Person(
        Long id,
        String personCode,
        PersonTypeCode personTypeCode,
        CoreStatus coreStatus,
        String primaryPhotoAssetId,
        String nombres,
        String apellidos,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

package com.solveria.peopleschool.governanceregistry.domain.orggroup;

import java.time.LocalDateTime;

public record OrgGroup(
        Long id,
        String tenantCode,
        String legalName,
        String commercialName,
        String taxIdentifier,
        String countryCode,
        String defaultCurrencyCode,
        String defaultTimezone,
        OrgGroupStatus orgGroupStatus,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

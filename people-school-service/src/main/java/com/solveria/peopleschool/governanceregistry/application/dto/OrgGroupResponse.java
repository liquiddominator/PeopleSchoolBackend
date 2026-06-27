package com.solveria.peopleschool.governanceregistry.application.dto;

import com.solveria.peopleschool.governanceregistry.domain.orggroup.OrgGroup;
import com.solveria.peopleschool.governanceregistry.domain.orggroup.OrgGroupStatus;
import java.time.LocalDateTime;

public record OrgGroupResponse(
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
        LocalDateTime lastModifiedAt) {

    public static OrgGroupResponse from(OrgGroup g) {
        return new OrgGroupResponse(
                g.id(),
                g.tenantCode(),
                g.legalName(),
                g.commercialName(),
                g.taxIdentifier(),
                g.countryCode(),
                g.defaultCurrencyCode(),
                g.defaultTimezone(),
                g.orgGroupStatus(),
                g.tenantId(),
                g.createdAt(),
                g.lastModifiedAt());
    }
}

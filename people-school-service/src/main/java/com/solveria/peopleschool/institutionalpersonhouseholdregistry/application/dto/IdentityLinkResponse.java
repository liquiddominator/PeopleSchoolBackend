package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personidentitylink.PersonIdentityLink;
import java.time.LocalDateTime;

public record IdentityLinkResponse(
        Long id,
        Long personId,
        String iamSubjectId,
        String identityProviderCode,
        String identityLinkStatus,
        LocalDateTime linkedAt,
        String linkedBy,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {

    public static IdentityLinkResponse from(PersonIdentityLink link) {
        return new IdentityLinkResponse(
                link.id(),
                link.personId(),
                link.iamSubjectId(),
                link.identityProviderCode(),
                link.identityLinkStatus().name(),
                link.linkedAt(),
                link.linkedBy(),
                link.tenantId(),
                link.createdAt(),
                link.createdBy(),
                link.lastModifiedAt(),
                link.lastModifiedBy(),
                link.version());
    }
}

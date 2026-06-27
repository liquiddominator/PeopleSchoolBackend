package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personidentitylink;

import java.time.LocalDateTime;

public record PersonIdentityLink(
        Long id,
        Long personId,
        String iamSubjectId,
        String identityProviderCode,
        IdentityLinkStatus identityLinkStatus,
        LocalDateTime linkedAt,
        String linkedBy,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.legalidentity;

public record PersonLegalIdentity(
        Long id,
        Long personId,
        DocumentType tipo,
        String numero,
        String paisEmisor,
        Boolean esPrincipal,
        IdentityStatus estado,
        String tenantId,
        Long version) {}

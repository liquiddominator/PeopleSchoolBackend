package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.contact;

public record PersonContact(
        Long id,
        Long personId,
        ContactType tipo,
        String valor,
        ContactUse uso,
        Boolean esPrincipal,
        String tenantId,
        Long version) {}

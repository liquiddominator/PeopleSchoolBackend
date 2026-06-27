package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.address;

public record PersonAddress(
        Long id,
        Long personId,
        String pais,
        String ciudad,
        String linea1,
        String linea2,
        Boolean esPrincipal,
        String tenantId,
        Long version) {}

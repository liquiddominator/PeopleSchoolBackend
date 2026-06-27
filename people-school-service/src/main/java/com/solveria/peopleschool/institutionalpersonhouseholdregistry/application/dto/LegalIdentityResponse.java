package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.legalidentity.PersonLegalIdentity;

public record LegalIdentityResponse(
        String id,
        String tipo,
        String numero,
        String paisEmisor,
        Boolean esPrincipal,
        String estado) {

    public static LegalIdentityResponse from(PersonLegalIdentity d) {
        return new LegalIdentityResponse(
                String.valueOf(d.id()),
                d.tipo().name(),
                d.numero(),
                d.paisEmisor(),
                d.esPrincipal(),
                d.estado().name());
    }
}

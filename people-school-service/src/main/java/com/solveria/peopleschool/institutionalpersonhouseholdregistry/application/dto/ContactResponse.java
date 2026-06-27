package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.contact.PersonContact;

public record ContactResponse(
        String id, String tipo, String valor, String uso, Boolean esPrincipal) {

    public static ContactResponse from(PersonContact d) {
        return new ContactResponse(
                String.valueOf(d.id()),
                d.tipo().name(),
                d.valor(),
                d.uso().name(),
                d.esPrincipal());
    }
}

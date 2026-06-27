package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.address.PersonAddress;

public record AddressResponse(
        String id, String pais, String ciudad, String linea1, String linea2, Boolean esPrincipal) {

    public static AddressResponse from(PersonAddress d) {
        return new AddressResponse(
                String.valueOf(d.id()),
                d.pais(),
                d.ciudad(),
                d.linea1(),
                d.linea2(),
                d.esPrincipal());
    }
}

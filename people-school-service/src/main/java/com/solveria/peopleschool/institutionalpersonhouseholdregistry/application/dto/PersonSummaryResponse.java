package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.CoreStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.Person;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.PersonTypeCode;

public record PersonSummaryResponse(
        Long id,
        String personCode,
        PersonTypeCode personTypeCode,
        CoreStatus coreStatus,
        String nombres,
        String apellidos) {

    public static PersonSummaryResponse from(Person person) {
        return new PersonSummaryResponse(
                person.id(),
                person.personCode(),
                person.personTypeCode(),
                person.coreStatus(),
                person.nombres(),
                person.apellidos());
    }
}

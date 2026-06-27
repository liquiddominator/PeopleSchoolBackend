package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.CoreStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.Person;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.PersonTypeCode;
import java.time.LocalDateTime;

public record PersonResponse(
        Long id,
        String personCode,
        PersonTypeCode personTypeCode,
        CoreStatus coreStatus,
        String primaryPhotoAssetId,
        String nombres,
        String apellidos,
        String tenantId,
        LocalDateTime createdAt,
        LocalDateTime lastModifiedAt) {

    public static PersonResponse from(Person person) {
        return new PersonResponse(
                person.id(),
                person.personCode(),
                person.personTypeCode(),
                person.coreStatus(),
                person.primaryPhotoAssetId(),
                person.nombres(),
                person.apellidos(),
                person.tenantId(),
                person.createdAt(),
                person.lastModifiedAt());
    }
}

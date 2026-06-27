package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.CoreStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.PersonTypeCode;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdatePersonRequest(
        @NotNull PersonTypeCode personTypeCode,
        @NotNull CoreStatus coreStatus,
        @Size(max = 255) String primaryPhotoAssetId,
        @Size(max = 120) String nombres,
        @Size(max = 120) String apellidos) {}

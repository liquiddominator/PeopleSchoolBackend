package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LegalIdentityRequest(
        @NotBlank String tipo,
        @NotBlank String numero,
        @NotBlank String paisEmisor,
        @NotNull Boolean esPrincipal,
        @NotBlank String estado) {}

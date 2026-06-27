package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContactRequest(
        @NotBlank String tipo,
        @NotBlank String valor,
        @NotBlank String uso,
        @NotNull Boolean esPrincipal) {}

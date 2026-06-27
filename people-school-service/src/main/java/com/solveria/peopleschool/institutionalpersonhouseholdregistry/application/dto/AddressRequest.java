package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressRequest(
        @NotBlank String pais,
        @NotBlank String ciudad,
        @NotBlank String linea1,
        String linea2,
        @NotNull Boolean esPrincipal) {}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateConflictStatusRequest(@NotBlank String estado) {}

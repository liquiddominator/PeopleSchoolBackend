package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

public record RegistryStatsResponse(
        long totalActivos, long totalHouseholds, long totalGuardianias, long conflictosAbiertos) {}

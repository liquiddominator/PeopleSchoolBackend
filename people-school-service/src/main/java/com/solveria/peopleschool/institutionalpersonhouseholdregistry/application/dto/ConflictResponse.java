package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.conflict.PersonConflict;

public record ConflictResponse(
        String id,
        String tipo,
        String estado,
        String personaIdA,
        String personaIdB,
        String descripcion,
        Integer score,
        String detectadoEn) {

    public static ConflictResponse from(PersonConflict conflict) {
        return new ConflictResponse(
                String.valueOf(conflict.id()),
                conflict.conflictType().name(),
                conflict.conflictStatus().name(),
                String.valueOf(conflict.personAId()),
                conflict.personBId() != null ? String.valueOf(conflict.personBId()) : null,
                conflict.description(),
                conflict.score(),
                conflict.createdAt() != null ? conflict.createdAt().toString() : null);
    }
}

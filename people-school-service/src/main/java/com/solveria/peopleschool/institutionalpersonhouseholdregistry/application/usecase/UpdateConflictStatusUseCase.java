package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.UpdateConflictStatusRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.conflict.ConflictStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.conflict.PersonConflict;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.conflict.PersonConflictRepositoryPort;

public class UpdateConflictStatusUseCase {

    private final PersonConflictRepositoryPort conflictRepository;

    public UpdateConflictStatusUseCase(PersonConflictRepositoryPort conflictRepository) {
        this.conflictRepository = conflictRepository;
    }

    public PersonConflict execute(Long id, UpdateConflictStatusRequest request, String tenantId) {
        PersonConflict existing =
                conflictRepository
                        .findById(id)
                        .filter(c -> c.tenantId().equals(tenantId))
                        .orElseThrow(
                                () -> new EntityNotFoundException("PersonConflict", id.toString()));

        PersonConflict updated =
                new PersonConflict(
                        existing.id(),
                        existing.conflictType(),
                        ConflictStatus.valueOf(request.estado()),
                        existing.personAId(),
                        existing.personBId(),
                        existing.description(),
                        existing.score(),
                        existing.tenantId(),
                        existing.createdAt(),
                        existing.createdBy(),
                        null,
                        null,
                        existing.version());
        return conflictRepository.save(updated);
    }
}

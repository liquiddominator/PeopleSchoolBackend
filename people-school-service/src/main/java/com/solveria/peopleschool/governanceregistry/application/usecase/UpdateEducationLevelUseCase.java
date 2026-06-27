package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.governanceregistry.application.dto.UpdateEducationLevelRequest;
import com.solveria.peopleschool.governanceregistry.domain.educationlevel.EducationLevel;
import com.solveria.peopleschool.governanceregistry.domain.educationlevel.EducationLevelRepositoryPort;

public class UpdateEducationLevelUseCase {

    private final EducationLevelRepositoryPort repository;

    public UpdateEducationLevelUseCase(EducationLevelRepositoryPort repository) {
        this.repository = repository;
    }

    public EducationLevel execute(Long id, UpdateEducationLevelRequest request, String tenantId) {
        EducationLevel existing =
                repository
                        .findById(id)
                        .orElseThrow(
                                () -> new EntityNotFoundException("EducationLevel", id.toString()));
        EducationLevel updated =
                new EducationLevel(
                        existing.id(),
                        existing.educationUnitId(),
                        existing.levelCode(),
                        request.levelName(),
                        request.officialReferenceCode(),
                        request.levelSequence(),
                        request.levelStatus(),
                        tenantId,
                        existing.createdAt(),
                        existing.createdBy(),
                        null,
                        null,
                        existing.version());
        return repository.save(updated);
    }
}

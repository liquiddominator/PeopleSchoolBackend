package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.governanceregistry.domain.educationlevel.EducationLevelRepositoryPort;

public class DeleteEducationLevelUseCase {

    private final EducationLevelRepositoryPort repository;

    public DeleteEducationLevelUseCase(EducationLevelRepositoryPort repository) {
        this.repository = repository;
    }

    public void execute(Long id, String tenantId) {
        repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("EducationLevel", id.toString()));
        repository.deleteById(id);
    }
}

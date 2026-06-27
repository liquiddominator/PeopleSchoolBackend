package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.governanceregistry.domain.schoolyear.SchoolYearRepositoryPort;

public class DeleteSchoolYearUseCase {

    private final SchoolYearRepositoryPort repository;

    public DeleteSchoolYearUseCase(SchoolYearRepositoryPort repository) {
        this.repository = repository;
    }

    public void execute(Long id, String tenantId) {
        repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SchoolYear", id.toString()));
        repository.deleteById(id);
    }
}

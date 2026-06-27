package com.solveria.peopleschool.schoolregulationsregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.schoolregulationsregistry.domain.schoolregulation.SchoolRegulationRepositoryPort;

public class DeleteSchoolRegulationUseCase {

    private final SchoolRegulationRepositoryPort repository;

    public DeleteSchoolRegulationUseCase(SchoolRegulationRepositoryPort repository) {
        this.repository = repository;
    }

    public void execute(Long id, String tenantId) {
        repository
                .findById(id)
                .filter(r -> r.tenantId().equals(tenantId))
                .orElseThrow(() -> new EntityNotFoundException("SchoolRegulation", id.toString()));
        repository.deleteById(id);
    }
}

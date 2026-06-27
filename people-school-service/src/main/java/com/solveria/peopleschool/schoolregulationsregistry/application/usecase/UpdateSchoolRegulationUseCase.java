package com.solveria.peopleschool.schoolregulationsregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.schoolregulationsregistry.application.dto.UpdateSchoolRegulationRequest;
import com.solveria.peopleschool.schoolregulationsregistry.domain.schoolregulation.SchoolRegulation;
import com.solveria.peopleschool.schoolregulationsregistry.domain.schoolregulation.SchoolRegulationRepositoryPort;

public class UpdateSchoolRegulationUseCase {

    private final SchoolRegulationRepositoryPort repository;

    public UpdateSchoolRegulationUseCase(SchoolRegulationRepositoryPort repository) {
        this.repository = repository;
    }

    public SchoolRegulation execute(
            Long id, UpdateSchoolRegulationRequest request, String tenantId) {
        SchoolRegulation existing =
                repository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new EntityNotFoundException(
                                                "SchoolRegulation", id.toString()));
        SchoolRegulation updated =
                new SchoolRegulation(
                        existing.id(),
                        existing.educationUnitId(),
                        existing.regulationCode(),
                        request.title(),
                        request.description(),
                        request.regulationType(),
                        request.issuingAuthorityTypeCode(),
                        request.criticalityLevel(),
                        request.regulationStatus(),
                        tenantId,
                        existing.createdAt(),
                        existing.createdBy(),
                        null,
                        null,
                        existing.version());
        return repository.save(updated);
    }
}

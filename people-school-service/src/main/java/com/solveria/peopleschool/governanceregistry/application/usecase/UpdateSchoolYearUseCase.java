package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.governanceregistry.application.dto.UpdateSchoolYearRequest;
import com.solveria.peopleschool.governanceregistry.domain.schoolyear.SchoolYear;
import com.solveria.peopleschool.governanceregistry.domain.schoolyear.SchoolYearRepositoryPort;

public class UpdateSchoolYearUseCase {

    private final SchoolYearRepositoryPort repository;

    public UpdateSchoolYearUseCase(SchoolYearRepositoryPort repository) {
        this.repository = repository;
    }

    public SchoolYear execute(Long id, UpdateSchoolYearRequest request, String tenantId) {
        SchoolYear existing =
                repository
                        .findById(id)
                        .orElseThrow(
                                () -> new EntityNotFoundException("SchoolYear", id.toString()));
        SchoolYear updated =
                new SchoolYear(
                        existing.id(),
                        existing.educationUnitId(),
                        existing.schoolYearCode(),
                        request.schoolYearName(),
                        request.startDate(),
                        request.endDate(),
                        request.lifecycleStatus(),
                        existing.isCurrentDefault(),
                        existing.isVisibleForQuery(),
                        existing.isEditable(),
                        existing.isReportable(),
                        existing.isArchived(),
                        existing.contextPriority(),
                        tenantId,
                        existing.createdAt(),
                        existing.createdBy(),
                        null,
                        null,
                        existing.version());
        return repository.save(updated);
    }
}

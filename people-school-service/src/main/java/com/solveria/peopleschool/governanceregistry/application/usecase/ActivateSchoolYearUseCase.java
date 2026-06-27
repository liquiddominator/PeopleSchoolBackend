package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.governanceregistry.domain.schoolyear.SchoolYear;
import com.solveria.peopleschool.governanceregistry.domain.schoolyear.SchoolYearLifecycleStatus;
import com.solveria.peopleschool.governanceregistry.domain.schoolyear.SchoolYearRepositoryPort;

public class ActivateSchoolYearUseCase {

    private final SchoolYearRepositoryPort repository;

    public ActivateSchoolYearUseCase(SchoolYearRepositoryPort repository) {
        this.repository = repository;
    }

    public SchoolYear execute(Long id, String tenantId) {
        SchoolYear existing =
                repository
                        .findById(id)
                        .orElseThrow(
                                () -> new EntityNotFoundException("SchoolYear", id.toString()));
        repository.clearCurrentDefaultForTenant(tenantId);
        SchoolYear activated =
                new SchoolYear(
                        existing.id(),
                        existing.educationUnitId(),
                        existing.schoolYearCode(),
                        existing.schoolYearName(),
                        existing.startDate(),
                        existing.endDate(),
                        SchoolYearLifecycleStatus.ACTIVE,
                        true,
                        true,
                        existing.isEditable(),
                        existing.isReportable(),
                        false,
                        existing.contextPriority(),
                        tenantId,
                        existing.createdAt(),
                        existing.createdBy(),
                        null,
                        null,
                        existing.version());
        return repository.save(activated);
    }
}

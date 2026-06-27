package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.peopleschool.governanceregistry.domain.educationlevel.EducationLevel;
import com.solveria.peopleschool.governanceregistry.domain.educationlevel.EducationLevelRepositoryPort;
import java.util.List;

public class GetEducationLevelsUseCase {

    private final EducationLevelRepositoryPort repository;

    public GetEducationLevelsUseCase(EducationLevelRepositoryPort repository) {
        this.repository = repository;
    }

    public List<EducationLevel> execute(String tenantId) {
        return repository.findAllByTenantId(tenantId);
    }
}

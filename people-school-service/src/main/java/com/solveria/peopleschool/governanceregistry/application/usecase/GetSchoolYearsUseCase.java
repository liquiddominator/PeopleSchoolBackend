package com.solveria.peopleschool.governanceregistry.application.usecase;

import com.solveria.peopleschool.governanceregistry.domain.schoolyear.SchoolYear;
import com.solveria.peopleschool.governanceregistry.domain.schoolyear.SchoolYearRepositoryPort;
import java.util.List;

public class GetSchoolYearsUseCase {

    private final SchoolYearRepositoryPort repository;

    public GetSchoolYearsUseCase(SchoolYearRepositoryPort repository) {
        this.repository = repository;
    }

    public List<SchoolYear> execute(String tenantId) {
        return repository.findAllByTenantId(tenantId);
    }
}

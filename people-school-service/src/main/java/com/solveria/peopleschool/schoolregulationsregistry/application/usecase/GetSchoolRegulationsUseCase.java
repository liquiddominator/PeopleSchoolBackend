package com.solveria.peopleschool.schoolregulationsregistry.application.usecase;

import com.solveria.peopleschool.schoolregulationsregistry.domain.schoolregulation.SchoolRegulation;
import com.solveria.peopleschool.schoolregulationsregistry.domain.schoolregulation.SchoolRegulationRepositoryPort;
import java.util.List;

public class GetSchoolRegulationsUseCase {

    private final SchoolRegulationRepositoryPort repository;

    public GetSchoolRegulationsUseCase(SchoolRegulationRepositoryPort repository) {
        this.repository = repository;
    }

    public List<SchoolRegulation> execute(String tenantId) {
        return repository.findAllByTenantId(tenantId);
    }
}

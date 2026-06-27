package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreateSensitiveCoverageRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitivecoverage.CoverageStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitivecoverage.PersonSensitiveCoverage;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitivecoverage.PersonSensitiveCoverageRepositoryPort;
import java.time.LocalDate;

public class RegisterSensitiveCoverageUseCase {

    private final PersonSensitiveCoverageRepositoryPort repository;

    public RegisterSensitiveCoverageUseCase(PersonSensitiveCoverageRepositoryPort repository) {
        this.repository = repository;
    }

    public PersonSensitiveCoverage execute(
            CreateSensitiveCoverageRequest request, String tenantId) {
        PersonSensitiveCoverage coverage =
                new PersonSensitiveCoverage(
                        null,
                        request.personSensitiveProfileId(),
                        request.personId(),
                        request.coverageTypeCode(),
                        request.providerName(),
                        request.policyNumber(),
                        CoverageStatus.valueOf(request.coverageStatus()),
                        request.effectiveFrom() != null
                                ? LocalDate.parse(request.effectiveFrom())
                                : null,
                        request.effectiveTo() != null
                                ? LocalDate.parse(request.effectiveTo())
                                : null,
                        request.notes(),
                        tenantId,
                        null,
                        null,
                        null,
                        null,
                        null);
        return repository.save(coverage);
    }
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreateFinancialResponsibleRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.financialresponsiblerelationship.FinancialResponsibleRelationship;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.financialresponsiblerelationship.FinancialResponsibleRepositoryPort;

public class RegisterFinancialResponsibleUseCase {

    private final FinancialResponsibleRepositoryPort repository;

    public RegisterFinancialResponsibleUseCase(FinancialResponsibleRepositoryPort repository) {
        this.repository = repository;
    }

    public FinancialResponsibleRelationship execute(
            CreateFinancialResponsibleRequest request, String tenantId) {
        FinancialResponsibleRelationship relationship =
                new FinancialResponsibleRelationship(
                        null,
                        request.beneficiaryPersonId(),
                        request.responsiblePersonId(),
                        request.householdId(),
                        request.responsibilityStatus(),
                        request.effectiveFrom(),
                        request.effectiveTo(),
                        request.notes(),
                        tenantId,
                        null,
                        null,
                        null,
                        null,
                        null);
        return repository.save(relationship);
    }
}

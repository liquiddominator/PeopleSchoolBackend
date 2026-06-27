package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.financialresponsiblerelationship.FinancialResponsibleRelationship;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.financialresponsiblerelationship.FinancialResponsibleRepositoryPort;
import java.util.List;

public class GetFinancialResponsiblesUseCase {

    private final FinancialResponsibleRepositoryPort repository;

    public GetFinancialResponsiblesUseCase(FinancialResponsibleRepositoryPort repository) {
        this.repository = repository;
    }

    public List<FinancialResponsibleRelationship> execute(
            Long beneficiaryPersonId, String tenantId) {
        return repository.findAllByBeneficiaryPersonIdAndTenantId(beneficiaryPersonId, tenantId);
    }
}

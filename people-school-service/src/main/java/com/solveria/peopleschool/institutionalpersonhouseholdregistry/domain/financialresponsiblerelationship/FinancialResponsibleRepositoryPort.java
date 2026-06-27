package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.financialresponsiblerelationship;

import java.util.List;
import java.util.Optional;

public interface FinancialResponsibleRepositoryPort {
    FinancialResponsibleRelationship save(FinancialResponsibleRelationship relationship);

    Optional<FinancialResponsibleRelationship> findById(Long id);

    List<FinancialResponsibleRelationship> findAllByBeneficiaryPersonIdAndTenantId(
            Long beneficiaryPersonId, String tenantId);

    void deleteById(Long id);
}

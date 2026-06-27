package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialResponsibleJpaRepository
        extends JpaRepository<FinancialResponsibleJpaEntity, Long> {
    List<FinancialResponsibleJpaEntity> findAllByBeneficiaryPersonIdAndTenantId(
            Long beneficiaryPersonId, String tenantId);
}

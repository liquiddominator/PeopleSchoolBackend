package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionalAffiliationJpaRepository
        extends JpaRepository<InstitutionalAffiliationJpaEntity, Long> {
    List<InstitutionalAffiliationJpaEntity> findAllByPersonIdAndTenantId(
            Long personId, String tenantId);
}

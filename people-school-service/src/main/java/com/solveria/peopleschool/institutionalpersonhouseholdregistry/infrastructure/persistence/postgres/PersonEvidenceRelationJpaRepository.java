package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonEvidenceRelationJpaRepository
        extends JpaRepository<PersonEvidenceRelationJpaEntity, Long> {
    List<PersonEvidenceRelationJpaEntity> findAllByPersonIdAndTenantId(
            Long personId, String tenantId);
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDocumentReferenceJpaRepository
        extends JpaRepository<PersonDocumentReferenceJpaEntity, Long> {
    List<PersonDocumentReferenceJpaEntity> findAllByPersonIdAndTenantId(
            Long personId, String tenantId);
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonIdentityLinkJpaRepository
        extends JpaRepository<PersonIdentityLinkJpaEntity, Long> {
    List<PersonIdentityLinkJpaEntity> findAllByPersonIdAndTenantId(Long personId, String tenantId);
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonContactJpaRepository extends JpaRepository<PersonContactJpaEntity, Long> {

    List<PersonContactJpaEntity> findByPersonIdAndTenantId(Long personId, String tenantId);
}

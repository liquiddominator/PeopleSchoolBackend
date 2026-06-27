package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationUnitJpaRepository extends JpaRepository<EducationUnitJpaEntity, Long> {
    Optional<EducationUnitJpaEntity> findByTenantId(String tenantId);

    boolean existsByUnitCodeAndTenantId(String unitCode, String tenantId);
}

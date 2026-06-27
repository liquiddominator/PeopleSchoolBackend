package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationLevelJpaRepository extends JpaRepository<EducationLevelJpaEntity, Long> {
    List<EducationLevelJpaEntity> findAllByTenantIdOrderByLevelSequenceAsc(String tenantId);

    boolean existsByLevelCodeAndTenantId(String code, String tenantId);
}

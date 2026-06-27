package com.solveria.peopleschool.schoolregulationsregistry.infrastructure.persistence.postgres;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRegulationJpaRepository
        extends JpaRepository<SchoolRegulationJpaEntity, Long> {

    List<SchoolRegulationJpaEntity> findAllByTenantIdOrderByRegulationCodeAsc(String tenantId);

    boolean existsByRegulationCodeAndTenantId(String code, String tenantId);
}

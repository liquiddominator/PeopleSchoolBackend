package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SchoolYearJpaRepository extends JpaRepository<SchoolYearJpaEntity, Long> {
    List<SchoolYearJpaEntity> findAllByTenantId(String tenantId);

    boolean existsBySchoolYearCodeAndTenantId(String code, String tenantId);

    @Modifying
    @Transactional
    @Query(
            "UPDATE SchoolYearJpaEntity s SET s.isCurrentDefault = false WHERE s.tenantId = :tenantId")
    void clearCurrentDefaultForTenant(String tenantId);
}

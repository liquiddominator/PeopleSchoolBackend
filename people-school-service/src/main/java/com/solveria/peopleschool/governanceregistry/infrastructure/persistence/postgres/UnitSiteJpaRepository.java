package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UnitSiteJpaRepository extends JpaRepository<UnitSiteJpaEntity, Long> {
    List<UnitSiteJpaEntity> findAllByTenantIdOrderByIsMainSiteDescSiteNameAsc(String tenantId);

    boolean existsBySiteCodeAndTenantId(String siteCode, String tenantId);

    @Modifying
    @Transactional
    @Query(
            "update UnitSiteJpaEntity s set s.isMainSite = false where s.tenantId = :tenantId and s.isMainSite = true")
    void clearMainSiteForTenant(@Param("tenantId") String tenantId);
}

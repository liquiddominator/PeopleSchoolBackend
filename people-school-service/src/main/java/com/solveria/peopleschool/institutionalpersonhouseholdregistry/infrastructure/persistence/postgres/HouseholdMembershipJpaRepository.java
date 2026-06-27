package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HouseholdMembershipJpaRepository
        extends JpaRepository<HouseholdMembershipJpaEntity, Long> {

    Page<HouseholdMembershipJpaEntity> findByHouseholdIdAndTenantId(
            Long householdId, String tenantId, Pageable pageable);

    @Query(
            "SELECT COUNT(m) > 0 FROM HouseholdMembershipJpaEntity m "
                    + "WHERE m.householdId = :householdId "
                    + "AND m.personId = :personId "
                    + "AND m.membershipStatus = 'ACTIVE'")
    boolean existsActiveByHouseholdIdAndPersonId(
            @Param("householdId") Long householdId, @Param("personId") Long personId);
}

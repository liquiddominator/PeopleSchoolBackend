package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HouseholdRepositoryPort {

    Household save(Household household);

    Optional<Household> findById(Long id);

    boolean existsByHouseholdCodeAndTenantId(String householdCode, String tenantId);

    Page<Household> findAll(
            String tenantId, String householdTypeCode, String householdStatus, Pageable pageable);

    long countByTenantIdAndHouseholdStatus(String tenantId, HouseholdStatus householdStatus);

    void deleteById(Long id);
}

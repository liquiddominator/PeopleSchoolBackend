package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.householdmembership;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HouseholdMembershipRepositoryPort {

    HouseholdMembership save(HouseholdMembership membership);

    Optional<HouseholdMembership> findById(Long id);

    Page<HouseholdMembership> findByHouseholdId(
            Long householdId, String tenantId, Pageable pageable);

    boolean existsActiveByHouseholdIdAndPersonId(Long householdId, Long personId);

    void deleteById(Long id);
}

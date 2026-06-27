package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.householdmembership.HouseholdMembership;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.householdmembership.HouseholdMembershipRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class GetHouseholdMembersUseCase {

    private final HouseholdMembershipRepositoryPort membershipRepository;

    public GetHouseholdMembersUseCase(HouseholdMembershipRepositoryPort membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public Page<HouseholdMembership> execute(Long householdId, String tenantId, Pageable pageable) {
        return membershipRepository.findByHouseholdId(householdId, tenantId, pageable);
    }
}

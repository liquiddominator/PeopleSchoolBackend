package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.householdmembership.HouseholdMembershipRepositoryPort;

public class RemoveMemberFromHouseholdUseCase {

    private final HouseholdMembershipRepositoryPort membershipRepository;

    public RemoveMemberFromHouseholdUseCase(
            HouseholdMembershipRepositoryPort membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public void execute(Long membershipId, String tenantId) {
        membershipRepository
                .findById(membershipId)
                .filter(m -> m.tenantId().equals(tenantId))
                .orElseThrow(
                        () ->
                                new EntityNotFoundException(
                                        "HouseholdMembership", membershipId.toString()));
        membershipRepository.deleteById(membershipId);
    }
}

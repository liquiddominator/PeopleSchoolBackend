package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.UpdateMemberRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.householdmembership.HouseholdMembership;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.householdmembership.HouseholdMembershipRepositoryPort;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.householdmembership.MembershipRoleCode;

public class UpdateMembershipUseCase {

    private final HouseholdMembershipRepositoryPort membershipRepository;

    public UpdateMembershipUseCase(HouseholdMembershipRepositoryPort membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public HouseholdMembership execute(
            Long membershipId, UpdateMemberRequest request, String tenantId) {
        HouseholdMembership existing =
                membershipRepository
                        .findById(membershipId)
                        .filter(m -> m.tenantId().equals(tenantId))
                        .orElseThrow(
                                () ->
                                        new EntityNotFoundException(
                                                "HouseholdMembership", membershipId.toString()));

        HouseholdMembership updated =
                new HouseholdMembership(
                        existing.id(),
                        existing.householdId(),
                        existing.personId(),
                        MembershipRoleCode.valueOf(request.membershipRoleCode()),
                        existing.membershipStatus(),
                        existing.effectiveFrom(),
                        existing.effectiveTo(),
                        request.isPrimaryGuardianGroup(),
                        existing.tenantId(),
                        existing.createdAt(),
                        existing.createdBy(),
                        null,
                        null,
                        existing.version());
        return membershipRepository.save(updated);
    }
}

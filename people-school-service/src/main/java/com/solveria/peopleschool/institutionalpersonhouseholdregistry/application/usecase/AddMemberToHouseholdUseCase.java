package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.core.shared.exceptions.BusinessRuleViolationException;
import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.AddMemberRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.HouseholdRepositoryPort;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.householdmembership.HouseholdMembership;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.householdmembership.HouseholdMembershipRepositoryPort;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.householdmembership.MembershipStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.PersonRepositoryPort;

public class AddMemberToHouseholdUseCase {

    private final HouseholdMembershipRepositoryPort membershipRepository;
    private final HouseholdRepositoryPort householdRepository;
    private final PersonRepositoryPort personRepository;

    public AddMemberToHouseholdUseCase(
            HouseholdMembershipRepositoryPort membershipRepository,
            HouseholdRepositoryPort householdRepository,
            PersonRepositoryPort personRepository) {
        this.membershipRepository = membershipRepository;
        this.householdRepository = householdRepository;
        this.personRepository = personRepository;
    }

    public HouseholdMembership execute(
            Long householdId, AddMemberRequest request, String tenantId) {
        householdRepository
                .findById(householdId)
                .filter(h -> h.tenantId().equals(tenantId))
                .orElseThrow(
                        () -> new EntityNotFoundException("Household", householdId.toString()));

        personRepository
                .findById(request.personId())
                .filter(p -> p.tenantId().equals(tenantId))
                .orElseThrow(
                        () -> new EntityNotFoundException("Person", request.personId().toString()));

        if (membershipRepository.existsActiveByHouseholdIdAndPersonId(
                householdId, request.personId())) {
            throw new BusinessRuleViolationException("membership.already.active");
        }

        HouseholdMembership membership =
                new HouseholdMembership(
                        null,
                        householdId,
                        request.personId(),
                        request.membershipRoleCode(),
                        MembershipStatus.ACTIVE,
                        request.effectiveFrom(),
                        request.effectiveTo(),
                        request.isPrimaryGuardianGroup(),
                        tenantId,
                        null,
                        null,
                        null,
                        null,
                        null);
        return membershipRepository.save(membership);
    }
}

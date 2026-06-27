package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.householdmembership.HouseholdMembership;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.householdmembership.MembershipRoleCode;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.householdmembership.MembershipStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record HouseholdMemberResponse(
        Long id,
        Long householdId,
        Long personId,
        MembershipRoleCode membershipRoleCode,
        MembershipStatus membershipStatus,
        LocalDate effectiveFrom,
        LocalDate effectiveTo,
        boolean isPrimaryGuardianGroup,
        LocalDateTime createdAt,
        LocalDateTime lastModifiedAt) {

    public static HouseholdMemberResponse from(HouseholdMembership m) {
        return new HouseholdMemberResponse(
                m.id(),
                m.householdId(),
                m.personId(),
                m.membershipRoleCode(),
                m.membershipStatus(),
                m.effectiveFrom(),
                m.effectiveTo(),
                m.isPrimaryGuardianGroup(),
                m.createdAt(),
                m.lastModifiedAt());
    }
}

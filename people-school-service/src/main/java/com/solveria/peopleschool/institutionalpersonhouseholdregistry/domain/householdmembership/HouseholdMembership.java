package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.householdmembership;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record HouseholdMembership(
        Long id,
        Long householdId,
        Long personId,
        MembershipRoleCode membershipRoleCode,
        MembershipStatus membershipStatus,
        LocalDate effectiveFrom,
        LocalDate effectiveTo,
        boolean isPrimaryGuardianGroup,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

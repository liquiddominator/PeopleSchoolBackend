package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.householdmembership.MembershipRoleCode;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record AddMemberRequest(
        @NotNull Long personId,
        @NotNull MembershipRoleCode membershipRoleCode,
        @NotNull LocalDate effectiveFrom,
        LocalDate effectiveTo,
        boolean isPrimaryGuardianGroup) {}

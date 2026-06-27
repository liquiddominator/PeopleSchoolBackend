package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.guardianrelationship.GuardianAuthorityStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.guardianrelationship.GuardianRelationship;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.guardianrelationship.GuardianTypeCode;
import java.time.LocalDate;

public record GuardianRelationshipResponse(
        Long id,
        Long studentPersonId,
        Long guardianPersonId,
        Long householdId,
        GuardianTypeCode guardianTypeCode,
        GuardianAuthorityStatus legalAuthorityStatus,
        GuardianAuthorityStatus schoolAuthorityStatus,
        LocalDate effectiveFrom,
        LocalDate effectiveTo,
        String notes) {

    public static GuardianRelationshipResponse from(GuardianRelationship r) {
        return new GuardianRelationshipResponse(
                r.id(),
                r.studentPersonId(),
                r.guardianPersonId(),
                r.householdId(),
                r.guardianTypeCode(),
                r.legalAuthorityStatus(),
                r.schoolAuthorityStatus(),
                r.effectiveFrom(),
                r.effectiveTo(),
                r.notes());
    }
}

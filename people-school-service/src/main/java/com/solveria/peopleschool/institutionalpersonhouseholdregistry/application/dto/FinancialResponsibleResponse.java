package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.financialresponsiblerelationship.FinancialResponsibleRelationship;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.financialresponsiblerelationship.ResponsibilityStatus;
import java.time.LocalDate;

public record FinancialResponsibleResponse(
        Long id,
        Long beneficiaryPersonId,
        Long responsiblePersonId,
        Long householdId,
        ResponsibilityStatus responsibilityStatus,
        LocalDate effectiveFrom,
        LocalDate effectiveTo,
        String notes) {

    public static FinancialResponsibleResponse from(FinancialResponsibleRelationship r) {
        return new FinancialResponsibleResponse(
                r.id(),
                r.beneficiaryPersonId(),
                r.responsiblePersonId(),
                r.householdId(),
                r.responsibilityStatus(),
                r.effectiveFrom(),
                r.effectiveTo(),
                r.notes());
    }
}

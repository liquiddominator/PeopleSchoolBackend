package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.financialresponsiblerelationship.ResponsibilityStatus;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreateFinancialResponsibleRequest(
        @NotNull Long beneficiaryPersonId,
        @NotNull Long responsiblePersonId,
        Long householdId,
        @NotNull ResponsibilityStatus responsibilityStatus,
        LocalDate effectiveFrom,
        LocalDate effectiveTo,
        String notes) {}

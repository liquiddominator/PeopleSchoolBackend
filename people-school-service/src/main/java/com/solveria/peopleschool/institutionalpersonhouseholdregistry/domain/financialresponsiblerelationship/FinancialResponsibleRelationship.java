package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.financialresponsiblerelationship;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record FinancialResponsibleRelationship(
        Long id,
        Long beneficiaryPersonId,
        Long responsiblePersonId,
        Long householdId,
        ResponsibilityStatus responsibilityStatus,
        LocalDate effectiveFrom,
        LocalDate effectiveTo,
        String notes,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}

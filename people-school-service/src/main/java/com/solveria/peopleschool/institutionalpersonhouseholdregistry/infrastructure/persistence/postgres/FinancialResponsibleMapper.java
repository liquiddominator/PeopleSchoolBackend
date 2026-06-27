package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.financialresponsiblerelationship.FinancialResponsibleRelationship;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.financialresponsiblerelationship.ResponsibilityStatus;
import org.springframework.stereotype.Component;

@Component
public class FinancialResponsibleMapper {

    public FinancialResponsibleRelationship toDomain(FinancialResponsibleJpaEntity entity) {
        return new FinancialResponsibleRelationship(
                entity.getId(),
                entity.getBeneficiaryPersonId(),
                entity.getResponsiblePersonId(),
                entity.getHouseholdId(),
                ResponsibilityStatus.valueOf(entity.getResponsibilityStatus()),
                entity.getEffectiveFrom(),
                entity.getEffectiveTo(),
                entity.getNotes(),
                entity.getTenantId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getLastModifiedAt(),
                entity.getLastModifiedBy(),
                entity.getVersion());
    }

    public FinancialResponsibleJpaEntity toNewEntity(FinancialResponsibleRelationship domain) {
        FinancialResponsibleJpaEntity entity = new FinancialResponsibleJpaEntity();
        entity.setTenantId(domain.tenantId());
        entity.setBeneficiaryPersonId(domain.beneficiaryPersonId());
        entity.setResponsiblePersonId(domain.responsiblePersonId());
        entity.setHouseholdId(domain.householdId());
        entity.setResponsibilityStatus(domain.responsibilityStatus().name());
        entity.setEffectiveFrom(domain.effectiveFrom());
        entity.setEffectiveTo(domain.effectiveTo());
        entity.setNotes(domain.notes());
        return entity;
    }
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pc_financial_responsible_relationship")
@Getter
@Setter
@NoArgsConstructor
public class FinancialResponsibleJpaEntity extends BaseEntity {

    @Column(name = "beneficiary_person_id", nullable = false)
    private Long beneficiaryPersonId;

    @Column(name = "responsible_person_id", nullable = false)
    private Long responsiblePersonId;

    @Column(name = "household_id")
    private Long householdId;

    @Column(name = "responsibility_status", nullable = false, length = 50)
    private String responsibilityStatus;

    @Column(name = "effective_from")
    private LocalDate effectiveFrom;

    @Column(name = "effective_to")
    private LocalDate effectiveTo;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
}

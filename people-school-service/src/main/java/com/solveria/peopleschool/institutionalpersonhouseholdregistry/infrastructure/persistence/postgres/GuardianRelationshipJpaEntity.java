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
@Table(name = "pc_guardian_relationship")
@Getter
@Setter
@NoArgsConstructor
public class GuardianRelationshipJpaEntity extends BaseEntity {

    @Column(name = "student_person_id", nullable = false)
    private Long studentPersonId;

    @Column(name = "guardian_person_id", nullable = false)
    private Long guardianPersonId;

    @Column(name = "household_id")
    private Long householdId;

    @Column(name = "guardian_type_code", nullable = false, length = 50)
    private String guardianTypeCode;

    @Column(name = "legal_authority_status", nullable = false, length = 50)
    private String legalAuthorityStatus;

    @Column(name = "school_authority_status", nullable = false, length = 50)
    private String schoolAuthorityStatus;

    @Column(name = "effective_from")
    private LocalDate effectiveFrom;

    @Column(name = "effective_to")
    private LocalDate effectiveTo;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
}

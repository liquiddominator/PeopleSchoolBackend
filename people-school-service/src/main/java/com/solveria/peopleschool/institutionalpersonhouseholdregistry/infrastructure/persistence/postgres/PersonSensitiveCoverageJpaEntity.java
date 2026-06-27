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
@Table(name = "pc_person_sensitive_coverage")
@Getter
@Setter
@NoArgsConstructor
public class PersonSensitiveCoverageJpaEntity extends BaseEntity {

    @Column(name = "person_sensitive_profile_id", nullable = false)
    private Long personSensitiveProfileId;

    @Column(name = "person_id", nullable = false)
    private Long personId;

    @Column(name = "coverage_type_code", nullable = false, length = 50)
    private String coverageTypeCode;

    @Column(name = "provider_name", length = 255)
    private String providerName;

    @Column(name = "policy_number", length = 120)
    private String policyNumber;

    @Column(name = "coverage_status", nullable = false, length = 50)
    private String coverageStatus;

    @Column(name = "effective_from")
    private LocalDate effectiveFrom;

    @Column(name = "effective_to")
    private LocalDate effectiveTo;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
}

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
@Table(name = "pc_institutional_affiliation")
@Getter
@Setter
@NoArgsConstructor
public class InstitutionalAffiliationJpaEntity extends BaseEntity {

    @Column(name = "person_id", nullable = false)
    private Long personId;

    @Column(name = "affiliation_type_code", nullable = false, length = 50)
    private String affiliationTypeCode;

    @Column(name = "site_id")
    private Long siteId;

    @Column(name = "organizational_unit_id")
    private Long organizationalUnitId;

    @Column(name = "affiliation_status", nullable = false, length = 50)
    private String affiliationStatus;

    @Column(name = "effective_from")
    private LocalDate effectiveFrom;

    @Column(name = "effective_to")
    private LocalDate effectiveTo;
}

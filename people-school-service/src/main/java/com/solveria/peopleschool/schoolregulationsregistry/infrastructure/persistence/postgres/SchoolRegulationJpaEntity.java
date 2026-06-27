package com.solveria.peopleschool.schoolregulationsregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pc_school_regulation")
@Getter
@Setter
@NoArgsConstructor
public class SchoolRegulationJpaEntity extends BaseEntity {

    @Column(name = "education_unit_id", nullable = false)
    private Long educationUnitId;

    @Column(name = "regulation_code", nullable = false, length = 100)
    private String regulationCode;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "regulation_type_code", nullable = false, length = 50)
    private String regulationTypeCode;

    @Column(name = "issuing_authority_type_code", length = 50)
    private String issuingAuthorityTypeCode;

    @Column(name = "criticality_level", nullable = false, length = 50)
    private String criticalityLevel;

    @Column(name = "regulation_status", nullable = false, length = 50)
    private String regulationStatus;
}

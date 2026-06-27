package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pc_education_level")
@Getter
@Setter
@NoArgsConstructor
public class EducationLevelJpaEntity extends BaseEntity {

    @Column(name = "education_unit_id", nullable = false)
    private Long educationUnitId;

    @Column(name = "level_code", nullable = false, length = 40)
    private String levelCode;

    @Column(name = "level_name", nullable = false, length = 120)
    private String levelName;

    @Column(name = "official_reference_code", length = 60)
    private String officialReferenceCode;

    @Column(name = "level_sequence", nullable = false)
    private int levelSequence;

    @Column(name = "level_status", nullable = false, length = 30)
    private String levelStatus;
}

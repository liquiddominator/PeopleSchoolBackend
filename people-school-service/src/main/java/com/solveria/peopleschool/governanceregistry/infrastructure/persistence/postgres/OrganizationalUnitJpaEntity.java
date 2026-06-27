package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pc_organizational_unit")
@Getter
@Setter
@NoArgsConstructor
public class OrganizationalUnitJpaEntity extends BaseEntity {

    @Column(name = "education_unit_id", nullable = false)
    private Long educationUnitId;

    @Column(name = "parent_organizational_unit_id")
    private Long parentOrgUnitId;

    @Column(name = "org_unit_code", nullable = false, length = 60)
    private String orgUnitCode;

    @Column(name = "org_unit_name", nullable = false, length = 180)
    private String orgUnitName;

    @Column(name = "org_unit_type", nullable = false, length = 50)
    private String orgUnitType;

    @Column(name = "display_order", nullable = false)
    private int displayOrder = 0;

    @Column(name = "unit_scope_status", nullable = false, length = 30)
    private String unitScopeStatus;
}

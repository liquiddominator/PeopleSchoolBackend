package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pc_institutional_role_catalog")
@Getter
@Setter
@NoArgsConstructor
public class RoleCatalogJpaEntity extends BaseEntity {

    @Column(name = "education_unit_id", nullable = false)
    private Long educationUnitId;

    @Column(name = "role_code", nullable = false, length = 60)
    private String roleCode;

    @Column(name = "role_name", nullable = false, length = 180)
    private String roleName;

    @Column(name = "role_family", nullable = false, length = 60)
    private String roleFamily;

    @Column(name = "is_assignable_to_person", nullable = false)
    private boolean isAssignableToPerson = true;

    @Column(name = "role_catalog_status", nullable = false, length = 30)
    private String roleCatalogStatus;

    @Column(name = "display_order", nullable = false)
    private int displayOrder = 0;
}

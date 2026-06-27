package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pc_person")
@Getter
@Setter
@NoArgsConstructor
public class PersonJpaEntity extends BaseEntity {

    @Column(name = "person_code", nullable = false, length = 50)
    private String personCode;

    @Column(name = "person_type_code", nullable = false, length = 30)
    private String personTypeCode;

    @Column(name = "core_status", nullable = false, length = 20)
    private String coreStatus;

    @Column(name = "primary_photo_asset_id", length = 255)
    private String primaryPhotoAssetId;

    @Column(name = "nombres", length = 120)
    private String nombres;

    @Column(name = "apellidos", length = 120)
    private String apellidos;
}

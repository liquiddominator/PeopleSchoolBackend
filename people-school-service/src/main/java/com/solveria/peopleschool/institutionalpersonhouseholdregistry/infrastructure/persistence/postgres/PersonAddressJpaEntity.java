package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pc_person_address")
@Getter
@Setter
@NoArgsConstructor
public class PersonAddressJpaEntity extends BaseEntity {

    @Column(name = "person_id", nullable = false)
    private Long personId;

    @Column(name = "pais", nullable = false, length = 60)
    private String pais;

    @Column(name = "ciudad", nullable = false, length = 80)
    private String ciudad;

    @Column(name = "linea1", nullable = false, length = 200)
    private String linea1;

    @Column(name = "linea2", length = 200)
    private String linea2;

    @Column(name = "es_principal", nullable = false)
    private Boolean esPrincipal;
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pc_person_contact")
@Getter
@Setter
@NoArgsConstructor
public class PersonContactJpaEntity extends BaseEntity {

    @Column(name = "person_id", nullable = false)
    private Long personId;

    @Column(name = "tipo", nullable = false, length = 20)
    private String tipo;

    @Column(name = "valor", nullable = false, length = 120)
    private String valor;

    @Column(name = "uso", nullable = false, length = 20)
    private String uso;

    @Column(name = "es_principal", nullable = false)
    private Boolean esPrincipal;
}

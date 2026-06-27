package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pc_person_legal_identity")
@Getter
@Setter
@NoArgsConstructor
public class PersonLegalIdentityJpaEntity extends BaseEntity {

    @Column(name = "person_id", nullable = false)
    private Long personId;

    @Column(name = "tipo", nullable = false, length = 20)
    private String tipo;

    @Column(name = "numero", nullable = false, length = 30)
    private String numero;

    @Column(name = "pais_emisor", nullable = false, length = 60)
    private String paisEmisor;

    @Column(name = "es_principal", nullable = false)
    private Boolean esPrincipal;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;
}

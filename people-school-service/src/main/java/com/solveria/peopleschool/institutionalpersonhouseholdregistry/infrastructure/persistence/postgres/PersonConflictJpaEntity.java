package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pc_person_conflict")
@Getter
@Setter
@NoArgsConstructor
public class PersonConflictJpaEntity extends BaseEntity {

    @Column(name = "conflict_type", nullable = false, length = 30)
    private String conflictType;

    @Column(name = "conflict_status", nullable = false, length = 20)
    private String conflictStatus;

    @Column(name = "person_a_id", nullable = false)
    private Long personAId;

    @Column(name = "person_b_id")
    private Long personBId;

    @Column(name = "description", length = 400)
    private String description;

    @Column(name = "score")
    private Integer score;
}

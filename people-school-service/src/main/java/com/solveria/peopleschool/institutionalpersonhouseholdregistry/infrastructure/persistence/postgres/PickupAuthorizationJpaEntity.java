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
@Table(name = "pc_pickup_authorization")
@Getter
@Setter
@NoArgsConstructor
public class PickupAuthorizationJpaEntity extends BaseEntity {

    @Column(name = "student_person_id", nullable = false)
    private Long studentPersonId;

    @Column(name = "authorized_person_id", nullable = false)
    private Long authorizedPersonId;

    @Column(name = "authorization_status", nullable = false, length = 50)
    private String authorizationStatus;

    @Column(name = "effective_from")
    private LocalDate effectiveFrom;

    @Column(name = "effective_to")
    private LocalDate effectiveTo;

    @Column(name = "authorization_scope_code", length = 50)
    private String authorizationScopeCode;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
}

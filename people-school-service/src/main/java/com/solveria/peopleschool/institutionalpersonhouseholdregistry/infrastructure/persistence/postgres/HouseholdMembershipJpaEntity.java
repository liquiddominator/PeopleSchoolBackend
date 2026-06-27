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
@Table(name = "pc_household_membership")
@Getter
@Setter
@NoArgsConstructor
public class HouseholdMembershipJpaEntity extends BaseEntity {

    @Column(name = "household_id", nullable = false)
    private Long householdId;

    @Column(name = "person_id", nullable = false)
    private Long personId;

    @Column(name = "membership_role_code", nullable = false, length = 30)
    private String membershipRoleCode;

    @Column(name = "membership_status", nullable = false, length = 20)
    private String membershipStatus;

    @Column(name = "effective_from", nullable = false)
    private LocalDate effectiveFrom;

    @Column(name = "effective_to")
    private LocalDate effectiveTo;

    @Column(name = "is_primary_guardian_group", nullable = false)
    private boolean isPrimaryGuardianGroup;
}

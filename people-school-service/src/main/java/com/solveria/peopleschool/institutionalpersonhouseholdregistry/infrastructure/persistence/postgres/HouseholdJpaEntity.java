package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pc_household")
@Getter
@Setter
@NoArgsConstructor
public class HouseholdJpaEntity extends BaseEntity {

    @Column(name = "household_code", nullable = false, length = 50)
    private String householdCode;

    @Column(name = "household_name", nullable = false, length = 255)
    private String householdName;

    @Column(name = "household_type_code", nullable = false, length = 30)
    private String householdTypeCode;

    @Column(name = "household_status", nullable = false, length = 20)
    private String householdStatus;

    @Column(name = "primary_billing_address_id")
    private Long primaryBillingAddressId;
}

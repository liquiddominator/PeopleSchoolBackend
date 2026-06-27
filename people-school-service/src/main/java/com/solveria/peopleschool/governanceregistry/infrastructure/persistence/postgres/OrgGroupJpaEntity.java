package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pc_org_group")
@Getter
@Setter
@NoArgsConstructor
public class OrgGroupJpaEntity extends BaseEntity {

    @Column(name = "tenant_code", nullable = false, length = 40)
    private String tenantCode;

    @Column(name = "legal_name", nullable = false, length = 220)
    private String legalName;

    @Column(name = "commercial_name", length = 220)
    private String commercialName;

    @Column(name = "tax_identifier", length = 60)
    private String taxIdentifier;

    @Column(name = "country_code", nullable = false, length = 10)
    private String countryCode;

    @Column(name = "default_currency_code", nullable = false, length = 10)
    private String defaultCurrencyCode;

    @Column(name = "default_timezone", nullable = false, length = 80)
    private String defaultTimezone;

    @Column(name = "org_group_status", nullable = false, length = 30)
    private String orgGroupStatus;
}

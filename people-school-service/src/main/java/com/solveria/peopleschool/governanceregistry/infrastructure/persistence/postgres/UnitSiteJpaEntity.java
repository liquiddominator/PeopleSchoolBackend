package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pc_unit_site")
@Getter
@Setter
@NoArgsConstructor
public class UnitSiteJpaEntity extends BaseEntity {

    @Column(name = "education_unit_id", nullable = false)
    private Long educationUnitId;

    @Column(name = "site_code", nullable = false, length = 50)
    private String siteCode;

    @Column(name = "site_name", nullable = false, length = 180)
    private String siteName;

    @Column(name = "address_line", length = 250)
    private String addressLine;

    @Column(name = "city_name", length = 120)
    private String cityName;

    @Column(name = "department_name", length = 120)
    private String departmentName;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @Column(name = "email_address", length = 150)
    private String emailAddress;

    @Column(name = "is_main_site", nullable = false)
    private boolean isMainSite;

    @Column(name = "site_status", nullable = false, length = 30)
    private String siteStatus;
}

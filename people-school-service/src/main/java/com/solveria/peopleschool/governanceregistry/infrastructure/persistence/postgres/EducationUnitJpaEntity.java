package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pc_education_unit")
@Getter
@Setter
@NoArgsConstructor
public class EducationUnitJpaEntity extends BaseEntity {

    @Column(name = "unit_code", nullable = false, length = 50)
    private String unitCode;

    @Column(name = "official_name", nullable = false, length = 220)
    private String officialName;

    @Column(name = "short_name", length = 120)
    private String shortName;

    @Column(name = "ministry_rue_code", length = 80)
    private String ministryRueCode;

    @Column(name = "administrative_dependency", nullable = false, length = 40)
    private String administrativeDependency;

    @Column(name = "institutional_type", nullable = false, length = 40)
    private String institutionalType;

    @Column(name = "service_type", nullable = false, length = 40)
    private String serviceType;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @Column(name = "email_address", length = 150)
    private String emailAddress;

    @Column(name = "address_line", length = 250)
    private String addressLine;

    @Column(name = "unit_status", nullable = false, length = 30)
    private String unitStatus;
}

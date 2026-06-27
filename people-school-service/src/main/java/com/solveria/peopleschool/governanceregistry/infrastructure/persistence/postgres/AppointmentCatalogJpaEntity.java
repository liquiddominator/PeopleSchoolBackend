package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pc_appointment_catalog")
@Getter
@Setter
@NoArgsConstructor
public class AppointmentCatalogJpaEntity extends BaseEntity {

    @Column(name = "education_unit_id", nullable = false)
    private Long educationUnitId;

    @Column(name = "appointment_code", nullable = false, length = 60)
    private String appointmentCode;

    @Column(name = "appointment_name", nullable = false, length = 180)
    private String appointmentName;

    @Column(name = "org_unit_type_scope", length = 60)
    private String orgUnitTypeScope;

    @Column(name = "requires_document_support", nullable = false)
    private boolean requiresDocumentSupport = false;

    @Column(name = "appointment_catalog_status", nullable = false, length = 30)
    private String appointmentCatalogStatus;

    @Column(name = "display_order", nullable = false)
    private int displayOrder = 0;
}

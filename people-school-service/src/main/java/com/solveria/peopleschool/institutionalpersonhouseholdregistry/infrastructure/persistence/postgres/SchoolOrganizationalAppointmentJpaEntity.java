package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pc_school_organizational_appointment")
@Getter
@Setter
@NoArgsConstructor
public class SchoolOrganizationalAppointmentJpaEntity extends BaseEntity {

    @Column(name = "person_id", nullable = false)
    private Long personId;

    @Column(name = "appointment_code", nullable = false, length = 50)
    private String appointmentCode;

    @Column(name = "organizational_unit_id")
    private Long organizationalUnitId;

    @Column(name = "appointment_status", nullable = false, length = 50)
    private String appointmentStatus;

    @Column(name = "started_at", nullable = false)
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pc_emergency_contact")
@Getter
@Setter
@NoArgsConstructor
public class EmergencyContactJpaEntity extends BaseEntity {

    @Column(name = "person_id", nullable = false)
    private Long personId;

    @Column(name = "contact_person_id")
    private Long contactPersonId;

    @Column(name = "emergency_priority", nullable = false)
    private Integer emergencyPriority;

    @Column(name = "relationship_label", length = 100)
    private String relationshipLabel;

    @Column(name = "emergency_status", nullable = false, length = 50)
    private String emergencyStatus;
}

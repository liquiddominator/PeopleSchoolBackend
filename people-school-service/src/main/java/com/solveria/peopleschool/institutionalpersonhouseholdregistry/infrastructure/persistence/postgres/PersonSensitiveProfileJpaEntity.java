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
@Table(name = "pc_person_sensitive_profile")
@Getter
@Setter
@NoArgsConstructor
public class PersonSensitiveProfileJpaEntity extends BaseEntity {

    @Column(name = "person_id", nullable = false, unique = true)
    private Long personId;

    @Column(name = "blood_type_code", length = 20)
    private String bloodTypeCode;

    @Column(name = "emergency_medical_notes", columnDefinition = "TEXT")
    private String emergencyMedicalNotes;

    @Column(name = "sensitive_profile_status", nullable = false, length = 50)
    private String sensitiveProfileStatus;

    @Column(name = "last_reviewed_at")
    private LocalDateTime lastReviewedAt;

    @Column(name = "last_reviewed_by", length = 100)
    private String lastReviewedBy;
}

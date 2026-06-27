package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pc_person_evidence_relation")
@Getter
@Setter
@NoArgsConstructor
public class PersonEvidenceRelationJpaEntity extends BaseEntity {

    @Column(name = "person_id")
    private Long personId;

    @Column(name = "household_id")
    private Long householdId;

    @Column(name = "related_context_type", nullable = false, length = 60)
    private String relatedContextType;

    @Column(name = "related_context_ref_id", nullable = false, length = 120)
    private String relatedContextRefId;

    @Column(name = "evidence_role_code", nullable = false, length = 60)
    private String evidenceRoleCode;

    @Column(name = "asset_id", nullable = false)
    private Long assetId;

    @Column(name = "evidence_status", nullable = false, length = 50)
    private String evidenceStatus;
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pc_person_document_reference")
@Getter
@Setter
@NoArgsConstructor
public class PersonDocumentReferenceJpaEntity extends BaseEntity {

    @Column(name = "person_id", nullable = false)
    private Long personId;

    @Column(name = "document_reference_type_code", nullable = false, length = 50)
    private String documentReferenceTypeCode;

    @Column(name = "asset_id", nullable = false)
    private Long assetId;

    @Column(name = "reference_status", nullable = false, length = 50)
    private String referenceStatus;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
}

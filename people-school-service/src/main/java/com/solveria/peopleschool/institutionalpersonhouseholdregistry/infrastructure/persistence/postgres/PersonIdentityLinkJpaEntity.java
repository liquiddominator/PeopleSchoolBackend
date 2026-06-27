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
@Table(name = "pc_person_identity_link")
@Getter
@Setter
@NoArgsConstructor
public class PersonIdentityLinkJpaEntity extends BaseEntity {

    @Column(name = "person_id", nullable = false)
    private Long personId;

    @Column(name = "iam_subject_id", nullable = false, length = 150)
    private String iamSubjectId;

    @Column(name = "identity_provider_code", length = 50)
    private String identityProviderCode;

    @Column(name = "identity_link_status", nullable = false, length = 50)
    private String identityLinkStatus;

    @Column(name = "linked_at", nullable = false)
    private LocalDateTime linkedAt;

    @Column(name = "linked_by", length = 100)
    private String linkedBy;
}

CREATE TABLE pc_person_evidence_relation (
    id                      BIGSERIAL    PRIMARY KEY,
    person_id               BIGINT,
    household_id            BIGINT,
    related_context_type    VARCHAR(60)  NOT NULL,
    related_context_ref_id  VARCHAR(120) NOT NULL,
    evidence_role_code      VARCHAR(60)  NOT NULL,
    asset_id                BIGINT       NOT NULL,
    evidence_status         VARCHAR(50)  NOT NULL,
    tenant_id               VARCHAR(36)  NOT NULL,
    version                 BIGINT       NOT NULL DEFAULT 0,
    created_at              TIMESTAMP,
    created_by              VARCHAR(100),
    last_modified_at        TIMESTAMP,
    last_modified_by        VARCHAR(100)
);

CREATE INDEX idx_evidence_rel_person    ON pc_person_evidence_relation (person_id);
CREATE INDEX idx_evidence_rel_household ON pc_person_evidence_relation (household_id);
CREATE INDEX idx_evidence_rel_tenant    ON pc_person_evidence_relation (tenant_id);

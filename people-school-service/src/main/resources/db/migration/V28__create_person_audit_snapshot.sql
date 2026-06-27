CREATE TABLE pc_person_audit_snapshot (
    id              BIGSERIAL    PRIMARY KEY,
    aggregate_id    BIGINT       NOT NULL,
    aggregate_type  VARCHAR(100) NOT NULL,
    snapshot_json   TEXT         NOT NULL,
    captured_at     TIMESTAMP    NOT NULL,
    captured_by     VARCHAR(100),
    person_id       BIGINT,
    tenant_id       VARCHAR(36)  NOT NULL,
    version         BIGINT       NOT NULL DEFAULT 0,
    created_at      TIMESTAMP,
    created_by      VARCHAR(100),
    last_modified_at TIMESTAMP,
    last_modified_by VARCHAR(100)
);

CREATE INDEX idx_audit_snapshot_aggregate ON pc_person_audit_snapshot (aggregate_id);
CREATE INDEX idx_audit_snapshot_person    ON pc_person_audit_snapshot (person_id);
CREATE INDEX idx_audit_snapshot_tenant    ON pc_person_audit_snapshot (tenant_id);

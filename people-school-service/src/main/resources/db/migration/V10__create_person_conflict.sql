CREATE TABLE pc_person_conflict (
    id               BIGSERIAL    PRIMARY KEY,
    conflict_type    VARCHAR(30)  NOT NULL,
    conflict_status  VARCHAR(20)  NOT NULL,
    person_a_id      BIGINT       NOT NULL,
    person_b_id      BIGINT,
    description      VARCHAR(400),
    score            INTEGER,
    tenant_id        VARCHAR(36)  NOT NULL,
    version          BIGINT       NOT NULL DEFAULT 0,
    created_at       TIMESTAMP,
    created_by       VARCHAR(100),
    last_modified_at TIMESTAMP,
    last_modified_by VARCHAR(100),
    CONSTRAINT fk_conflict_person_a FOREIGN KEY (person_a_id) REFERENCES pc_person (id),
    CONSTRAINT fk_conflict_person_b FOREIGN KEY (person_b_id) REFERENCES pc_person (id)
);

CREATE INDEX idx_conflict_tenant_id     ON pc_person_conflict (tenant_id);
CREATE INDEX idx_conflict_status        ON pc_person_conflict (conflict_status);
CREATE INDEX idx_conflict_person_a      ON pc_person_conflict (person_a_id);

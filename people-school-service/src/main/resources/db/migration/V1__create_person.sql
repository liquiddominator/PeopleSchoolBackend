CREATE TABLE pc_person (
    id                     BIGSERIAL PRIMARY KEY,
    person_code            VARCHAR(50)  NOT NULL,
    person_type_code       VARCHAR(30)  NOT NULL,
    core_status            VARCHAR(20)  NOT NULL,
    primary_photo_asset_id VARCHAR(255),
    tenant_id              VARCHAR(36)  NOT NULL,
    version                BIGINT       NOT NULL DEFAULT 0,
    created_at             TIMESTAMP,
    created_by             VARCHAR(100),
    last_modified_at       TIMESTAMP,
    last_modified_by       VARCHAR(100),
    CONSTRAINT uq_person_code_tenant UNIQUE (person_code, tenant_id)
);

CREATE INDEX idx_person_tenant_id      ON pc_person (tenant_id);
CREATE INDEX idx_person_type_code      ON pc_person (person_type_code);
CREATE INDEX idx_person_core_status    ON pc_person (core_status);
CREATE INDEX idx_person_code           ON pc_person (person_code);

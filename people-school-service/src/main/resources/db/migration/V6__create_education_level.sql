CREATE TABLE pc_education_level (
    id                      BIGSERIAL    PRIMARY KEY,
    education_unit_id       BIGINT       NOT NULL REFERENCES pc_education_unit(id),
    level_code              VARCHAR(40)  NOT NULL,
    level_name              VARCHAR(120) NOT NULL,
    official_reference_code VARCHAR(60),
    level_sequence          INTEGER      NOT NULL,
    level_status            VARCHAR(30)  NOT NULL,
    tenant_id               VARCHAR(36)  NOT NULL,
    version                 BIGINT       NOT NULL DEFAULT 0,
    created_at              TIMESTAMP,
    created_by              VARCHAR(100),
    last_modified_at        TIMESTAMP,
    last_modified_by        VARCHAR(100),
    CONSTRAINT uq_education_level_code_tenant UNIQUE (level_code, tenant_id),
    CONSTRAINT ck_education_level_sequence CHECK (level_sequence > 0)
);

CREATE INDEX idx_education_level_tenant          ON pc_education_level (tenant_id);
CREATE INDEX idx_education_level_education_unit  ON pc_education_level (education_unit_id);
CREATE INDEX idx_education_level_sequence        ON pc_education_level (tenant_id, level_sequence);

CREATE TABLE pc_school_regulation (
    id                              BIGSERIAL    PRIMARY KEY,
    education_unit_id               BIGINT       NOT NULL REFERENCES pc_education_unit(id),
    regulation_code                 VARCHAR(100) NOT NULL,
    title                           VARCHAR(255) NOT NULL,
    description                     TEXT,
    regulation_type_code            VARCHAR(50)  NOT NULL,
    issuing_authority_type_code     VARCHAR(50),
    criticality_level               VARCHAR(50)  NOT NULL,
    regulation_status               VARCHAR(50)  NOT NULL,
    tenant_id                       VARCHAR(36)  NOT NULL,
    version                         BIGINT       NOT NULL DEFAULT 0,
    created_at                      TIMESTAMP,
    created_by                      VARCHAR(100),
    last_modified_at                TIMESTAMP,
    last_modified_by                VARCHAR(100),
    CONSTRAINT uq_school_regulation_code_tenant UNIQUE (regulation_code, tenant_id)
);

CREATE INDEX idx_school_regulation_tenant        ON pc_school_regulation (tenant_id);
CREATE INDEX idx_school_regulation_education_unit ON pc_school_regulation (education_unit_id);
CREATE INDEX idx_school_regulation_status        ON pc_school_regulation (regulation_status);
CREATE INDEX idx_school_regulation_type          ON pc_school_regulation (regulation_type_code);

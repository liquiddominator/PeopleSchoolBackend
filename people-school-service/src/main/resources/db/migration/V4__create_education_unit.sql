CREATE TABLE pc_education_unit (
    id                         BIGSERIAL    PRIMARY KEY,
    unit_code                  VARCHAR(50)  NOT NULL,
    official_name              VARCHAR(220) NOT NULL,
    short_name                 VARCHAR(120),
    ministry_rue_code          VARCHAR(80),
    administrative_dependency  VARCHAR(40)  NOT NULL DEFAULT 'PRIVADO',
    institutional_type         VARCHAR(40)  NOT NULL DEFAULT 'PRIVADO',
    service_type               VARCHAR(40)  NOT NULL DEFAULT 'PRESENCIAL',
    phone_number               VARCHAR(50),
    email_address              VARCHAR(150),
    address_line               VARCHAR(250),
    unit_status                VARCHAR(30)  NOT NULL,
    tenant_id                  VARCHAR(36)  NOT NULL,
    version                    BIGINT       NOT NULL DEFAULT 0,
    created_at                 TIMESTAMP,
    created_by                 VARCHAR(100),
    last_modified_at           TIMESTAMP,
    last_modified_by           VARCHAR(100),
    CONSTRAINT uq_education_unit_code_tenant UNIQUE (unit_code, tenant_id)
);

CREATE INDEX idx_education_unit_tenant      ON pc_education_unit (tenant_id);
CREATE INDEX idx_education_unit_status      ON pc_education_unit (unit_status);

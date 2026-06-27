CREATE TABLE pc_institutional_role_catalog (
    id                      BIGSERIAL    PRIMARY KEY,
    education_unit_id       BIGINT       NOT NULL REFERENCES pc_education_unit(id),
    role_code               VARCHAR(60)  NOT NULL,
    role_name               VARCHAR(180) NOT NULL,
    role_family             VARCHAR(60)  NOT NULL,
    is_assignable_to_person BOOLEAN      NOT NULL DEFAULT TRUE,
    role_catalog_status     VARCHAR(30)  NOT NULL,
    display_order           INTEGER      NOT NULL DEFAULT 0,
    tenant_id               VARCHAR(36)  NOT NULL,
    version                 BIGINT       NOT NULL DEFAULT 0,
    created_at              TIMESTAMP,
    created_by              VARCHAR(100),
    last_modified_at        TIMESTAMP,
    last_modified_by        VARCHAR(100),
    CONSTRAINT uq_role_catalog_code_tenant UNIQUE (role_code, tenant_id),
    CONSTRAINT ck_role_catalog_display_order CHECK (display_order >= 0)
);

CREATE INDEX idx_role_catalog_tenant          ON pc_institutional_role_catalog (tenant_id);
CREATE INDEX idx_role_catalog_education_unit  ON pc_institutional_role_catalog (education_unit_id);
CREATE INDEX idx_role_catalog_family_status   ON pc_institutional_role_catalog (role_family, role_catalog_status);

CREATE TABLE pc_organizational_unit (
    id                           BIGSERIAL    PRIMARY KEY,
    education_unit_id            BIGINT       NOT NULL REFERENCES pc_education_unit(id),
    parent_organizational_unit_id BIGINT      REFERENCES pc_organizational_unit(id),
    org_unit_code                VARCHAR(60)  NOT NULL,
    org_unit_name                VARCHAR(180) NOT NULL,
    org_unit_type                VARCHAR(50)  NOT NULL,
    display_order                INTEGER      NOT NULL DEFAULT 0,
    unit_scope_status            VARCHAR(30)  NOT NULL,
    tenant_id                    VARCHAR(36)  NOT NULL,
    version                      BIGINT       NOT NULL DEFAULT 0,
    created_at                   TIMESTAMP,
    created_by                   VARCHAR(100),
    last_modified_at             TIMESTAMP,
    last_modified_by             VARCHAR(100),
    CONSTRAINT uq_org_unit_code_tenant UNIQUE (org_unit_code, tenant_id),
    CONSTRAINT ck_org_unit_display_order CHECK (display_order >= 0),
    CONSTRAINT ck_org_unit_not_self_parent CHECK (parent_organizational_unit_id IS NULL OR parent_organizational_unit_id <> id)
);

CREATE INDEX idx_org_unit_tenant          ON pc_organizational_unit (tenant_id);
CREATE INDEX idx_org_unit_education_unit  ON pc_organizational_unit (education_unit_id);
CREATE INDEX idx_org_unit_parent          ON pc_organizational_unit (parent_organizational_unit_id);
CREATE INDEX idx_org_unit_type_status     ON pc_organizational_unit (org_unit_type, unit_scope_status);

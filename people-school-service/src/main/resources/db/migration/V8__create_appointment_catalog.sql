CREATE TABLE pc_appointment_catalog (
    id                           BIGSERIAL    PRIMARY KEY,
    education_unit_id            BIGINT       NOT NULL REFERENCES pc_education_unit(id),
    appointment_code             VARCHAR(60)  NOT NULL,
    appointment_name             VARCHAR(180) NOT NULL,
    org_unit_type_scope          VARCHAR(60),
    requires_document_support    BOOLEAN      NOT NULL DEFAULT FALSE,
    appointment_catalog_status   VARCHAR(30)  NOT NULL,
    display_order                INTEGER      NOT NULL DEFAULT 0,
    tenant_id                    VARCHAR(36)  NOT NULL,
    version                      BIGINT       NOT NULL DEFAULT 0,
    created_at                   TIMESTAMP,
    created_by                   VARCHAR(100),
    last_modified_at             TIMESTAMP,
    last_modified_by             VARCHAR(100),
    CONSTRAINT uq_appointment_code_tenant UNIQUE (appointment_code, tenant_id),
    CONSTRAINT ck_appointment_display_order CHECK (display_order >= 0)
);

CREATE INDEX idx_appointment_catalog_tenant          ON pc_appointment_catalog (tenant_id);
CREATE INDEX idx_appointment_catalog_education_unit  ON pc_appointment_catalog (education_unit_id);
CREATE INDEX idx_appointment_catalog_status          ON pc_appointment_catalog (appointment_catalog_status);

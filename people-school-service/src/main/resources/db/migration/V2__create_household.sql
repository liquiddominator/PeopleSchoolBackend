CREATE TABLE pc_household (
    id                         BIGSERIAL PRIMARY KEY,
    household_code             VARCHAR(50)  NOT NULL,
    household_name             VARCHAR(255) NOT NULL,
    household_type_code        VARCHAR(30)  NOT NULL,
    household_status           VARCHAR(20)  NOT NULL,
    primary_billing_address_id BIGINT,
    tenant_id                  VARCHAR(36)  NOT NULL,
    version                    BIGINT       NOT NULL DEFAULT 0,
    created_at                 TIMESTAMP,
    created_by                 VARCHAR(100),
    last_modified_at           TIMESTAMP,
    last_modified_by           VARCHAR(100),
    CONSTRAINT uq_household_code_tenant UNIQUE (household_code, tenant_id)
);

CREATE INDEX idx_household_tenant_id   ON pc_household (tenant_id);
CREATE INDEX idx_household_status      ON pc_household (household_status);
CREATE INDEX idx_household_type_code   ON pc_household (household_type_code);

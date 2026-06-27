CREATE TABLE pc_org_group (
    id                    BIGSERIAL    PRIMARY KEY,
    tenant_code           VARCHAR(40)  NOT NULL,
    legal_name            VARCHAR(220) NOT NULL,
    commercial_name       VARCHAR(220),
    tax_identifier        VARCHAR(60),
    country_code          VARCHAR(10)  NOT NULL DEFAULT 'BO',
    default_currency_code VARCHAR(10)  NOT NULL DEFAULT 'BOB',
    default_timezone      VARCHAR(80)  NOT NULL DEFAULT 'America/La_Paz',
    org_group_status      VARCHAR(30)  NOT NULL,
    tenant_id             VARCHAR(36)  NOT NULL,
    version               BIGINT       NOT NULL DEFAULT 0,
    created_at            TIMESTAMP,
    created_by            VARCHAR(100),
    last_modified_at      TIMESTAMP,
    last_modified_by      VARCHAR(100),
    CONSTRAINT uq_org_group_tenant_code UNIQUE (tenant_code, tenant_id)
);

CREATE INDEX idx_org_group_tenant ON pc_org_group (tenant_id);

CREATE TABLE pc_unit_site (
    id                 BIGSERIAL    PRIMARY KEY,
    education_unit_id  BIGINT       NOT NULL REFERENCES pc_education_unit(id),
    site_code          VARCHAR(50)  NOT NULL,
    site_name          VARCHAR(180) NOT NULL,
    address_line       VARCHAR(250),
    city_name          VARCHAR(120),
    department_name    VARCHAR(120),
    phone_number       VARCHAR(50),
    email_address      VARCHAR(150),
    is_main_site       BOOLEAN      NOT NULL DEFAULT FALSE,
    site_status        VARCHAR(30)  NOT NULL,
    tenant_id          VARCHAR(36)  NOT NULL,
    version            BIGINT       NOT NULL DEFAULT 0,
    created_at         TIMESTAMP,
    created_by         VARCHAR(100),
    last_modified_at   TIMESTAMP,
    last_modified_by   VARCHAR(100),
    CONSTRAINT uq_unit_site_code_tenant UNIQUE (site_code, tenant_id)
);

CREATE INDEX idx_unit_site_tenant ON pc_unit_site (tenant_id);
CREATE INDEX idx_unit_site_education_unit ON pc_unit_site (education_unit_id);

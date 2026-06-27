CREATE TABLE pc_school_year (
    id                       BIGSERIAL    PRIMARY KEY,
    education_unit_id        BIGINT       NOT NULL REFERENCES pc_education_unit(id),
    school_year_code         VARCHAR(20)  NOT NULL,
    school_year_name         VARCHAR(120) NOT NULL,
    start_date               DATE         NOT NULL,
    end_date                 DATE         NOT NULL,
    lifecycle_status         VARCHAR(30)  NOT NULL,
    is_current_default       BOOLEAN      NOT NULL DEFAULT FALSE,
    is_visible_for_query     BOOLEAN      NOT NULL DEFAULT TRUE,
    is_editable              BOOLEAN      NOT NULL DEFAULT TRUE,
    is_reportable            BOOLEAN      NOT NULL DEFAULT TRUE,
    is_archived              BOOLEAN      NOT NULL DEFAULT FALSE,
    context_priority         INTEGER      NOT NULL DEFAULT 0,
    tenant_id                VARCHAR(36)  NOT NULL,
    version                  BIGINT       NOT NULL DEFAULT 0,
    created_at               TIMESTAMP,
    created_by               VARCHAR(100),
    last_modified_at         TIMESTAMP,
    last_modified_by         VARCHAR(100),
    CONSTRAINT uq_school_year_code_tenant UNIQUE (school_year_code, tenant_id),
    CONSTRAINT ck_school_year_dates CHECK (end_date >= start_date),
    CONSTRAINT ck_school_year_priority CHECK (context_priority >= 0)
);

CREATE INDEX idx_school_year_tenant             ON pc_school_year (tenant_id);
CREATE INDEX idx_school_year_status             ON pc_school_year (lifecycle_status);
CREATE INDEX idx_school_year_education_unit     ON pc_school_year (education_unit_id);
CREATE UNIQUE INDEX uk_school_year_current_default ON pc_school_year (tenant_id) WHERE is_current_default = TRUE;

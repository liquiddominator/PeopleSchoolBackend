CREATE TABLE pc_person_sensitive_profile (
    id                          BIGSERIAL    PRIMARY KEY,
    person_id                   BIGINT       NOT NULL UNIQUE,
    blood_type_code             VARCHAR(20),
    emergency_medical_notes     TEXT,
    sensitive_profile_status    VARCHAR(50)  NOT NULL,
    last_reviewed_at            TIMESTAMP,
    last_reviewed_by            VARCHAR(100),
    tenant_id                   VARCHAR(36)  NOT NULL,
    version                     BIGINT       NOT NULL DEFAULT 0,
    created_at                  TIMESTAMP,
    created_by                  VARCHAR(100),
    last_modified_at            TIMESTAMP,
    last_modified_by            VARCHAR(100),
    CONSTRAINT fk_sensitive_profile_person FOREIGN KEY (person_id) REFERENCES pc_person(id)
);

CREATE INDEX idx_sensitive_profile_person ON pc_person_sensitive_profile (person_id);
CREATE INDEX idx_sensitive_profile_tenant ON pc_person_sensitive_profile (tenant_id);

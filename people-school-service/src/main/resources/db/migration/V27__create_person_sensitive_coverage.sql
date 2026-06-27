CREATE TABLE pc_person_sensitive_coverage (
    id                          BIGSERIAL    PRIMARY KEY,
    person_sensitive_profile_id BIGINT       NOT NULL,
    person_id                   BIGINT       NOT NULL,
    coverage_type_code          VARCHAR(50)  NOT NULL,
    provider_name               VARCHAR(255),
    policy_number               VARCHAR(120),
    coverage_status             VARCHAR(50)  NOT NULL,
    effective_from              DATE,
    effective_to                DATE,
    notes                       TEXT,
    tenant_id                   VARCHAR(36)  NOT NULL,
    version                     BIGINT       NOT NULL DEFAULT 0,
    created_at                  TIMESTAMP,
    created_by                  VARCHAR(100),
    last_modified_at            TIMESTAMP,
    last_modified_by            VARCHAR(100),
    CONSTRAINT fk_sensitive_coverage_profile FOREIGN KEY (person_sensitive_profile_id) REFERENCES pc_person_sensitive_profile(id),
    CONSTRAINT fk_sensitive_coverage_person  FOREIGN KEY (person_id) REFERENCES pc_person(id)
);

CREATE INDEX idx_sensitive_coverage_profile ON pc_person_sensitive_coverage (person_sensitive_profile_id);
CREATE INDEX idx_sensitive_coverage_person  ON pc_person_sensitive_coverage (person_id);
CREATE INDEX idx_sensitive_coverage_tenant  ON pc_person_sensitive_coverage (tenant_id);

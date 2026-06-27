CREATE TABLE pc_student_profile (
    id                          BIGSERIAL   PRIMARY KEY,
    person_id                   BIGINT      NOT NULL REFERENCES pc_person(id),
    student_code                VARCHAR(80) NOT NULL,
    student_status              VARCHAR(40) NOT NULL,
    school_entry_date           DATE,
    first_school_year_id        BIGINT      REFERENCES pc_school_year(id),
    current_operational_status  VARCHAR(40) NOT NULL,
    tenant_id                   VARCHAR(36) NOT NULL,
    version                     BIGINT      NOT NULL DEFAULT 0,
    created_at                  TIMESTAMP,
    created_by                  VARCHAR(100),
    last_modified_at            TIMESTAMP,
    last_modified_by            VARCHAR(100),
    CONSTRAINT uq_student_profile_person_tenant UNIQUE (person_id, tenant_id),
    CONSTRAINT uq_student_profile_code_tenant   UNIQUE (student_code, tenant_id)
);

CREATE INDEX idx_student_profile_tenant        ON pc_student_profile (tenant_id);
CREATE INDEX idx_student_profile_person        ON pc_student_profile (person_id);
CREATE INDEX idx_student_profile_status        ON pc_student_profile (student_status);
CREATE INDEX idx_student_profile_school_year   ON pc_student_profile (first_school_year_id);

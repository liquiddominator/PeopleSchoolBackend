CREATE TABLE pc_guardian_relationship (
    id                      BIGSERIAL    PRIMARY KEY,
    student_person_id       BIGINT       NOT NULL,
    guardian_person_id      BIGINT       NOT NULL,
    household_id            BIGINT,
    guardian_type_code      VARCHAR(50)  NOT NULL,
    legal_authority_status  VARCHAR(50)  NOT NULL,
    school_authority_status VARCHAR(50)  NOT NULL,
    effective_from          DATE,
    effective_to            DATE,
    notes                   TEXT,
    tenant_id               VARCHAR(36)  NOT NULL,
    version                 BIGINT       NOT NULL DEFAULT 0,
    created_at              TIMESTAMP,
    created_by              VARCHAR(100),
    last_modified_at        TIMESTAMP,
    last_modified_by        VARCHAR(100),
    CONSTRAINT fk_guardian_student  FOREIGN KEY (student_person_id)  REFERENCES pc_person(id),
    CONSTRAINT fk_guardian_guardian FOREIGN KEY (guardian_person_id) REFERENCES pc_person(id),
    CONSTRAINT fk_guardian_household FOREIGN KEY (household_id)      REFERENCES pc_household(id)
);

CREATE INDEX idx_guardian_rel_student  ON pc_guardian_relationship (student_person_id);
CREATE INDEX idx_guardian_rel_guardian ON pc_guardian_relationship (guardian_person_id);
CREATE INDEX idx_guardian_rel_tenant   ON pc_guardian_relationship (tenant_id);

CREATE TABLE pc_institutional_role_assignment (
    id                     BIGSERIAL    PRIMARY KEY,
    person_id              BIGINT       NOT NULL,
    role_code              VARCHAR(50)  NOT NULL,
    role_status            VARCHAR(50)  NOT NULL,
    effective_from         DATE,
    effective_to           DATE,
    tenant_id              VARCHAR(36)  NOT NULL,
    version                BIGINT       NOT NULL DEFAULT 0,
    created_at             TIMESTAMP,
    created_by             VARCHAR(100),
    last_modified_at       TIMESTAMP,
    last_modified_by       VARCHAR(100),
    CONSTRAINT fk_role_assignment_person FOREIGN KEY (person_id) REFERENCES pc_person(id)
);

CREATE INDEX idx_role_assignment_person    ON pc_institutional_role_assignment (person_id);
CREATE INDEX idx_role_assignment_role_code ON pc_institutional_role_assignment (role_code, role_status);
CREATE INDEX idx_role_assignment_tenant    ON pc_institutional_role_assignment (tenant_id);

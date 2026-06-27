CREATE TABLE pc_pickup_authorization (
    id                       BIGSERIAL    PRIMARY KEY,
    student_person_id        BIGINT       NOT NULL,
    authorized_person_id     BIGINT       NOT NULL,
    authorization_status     VARCHAR(50)  NOT NULL,
    effective_from           DATE,
    effective_to             DATE,
    authorization_scope_code VARCHAR(50),
    notes                    TEXT,
    tenant_id                VARCHAR(36)  NOT NULL,
    version                  BIGINT       NOT NULL DEFAULT 0,
    created_at               TIMESTAMP,
    created_by               VARCHAR(100),
    last_modified_at         TIMESTAMP,
    last_modified_by         VARCHAR(100),
    CONSTRAINT fk_pickup_student    FOREIGN KEY (student_person_id)    REFERENCES pc_person(id),
    CONSTRAINT fk_pickup_authorized FOREIGN KEY (authorized_person_id) REFERENCES pc_person(id)
);

CREATE INDEX idx_pickup_auth_student    ON pc_pickup_authorization (student_person_id);
CREATE INDEX idx_pickup_auth_authorized ON pc_pickup_authorization (authorized_person_id);
CREATE INDEX idx_pickup_auth_tenant     ON pc_pickup_authorization (tenant_id);

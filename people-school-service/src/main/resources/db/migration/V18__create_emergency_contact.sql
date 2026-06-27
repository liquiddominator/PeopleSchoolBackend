CREATE TABLE pc_emergency_contact (
    id                   BIGSERIAL    PRIMARY KEY,
    person_id            BIGINT       NOT NULL,
    contact_person_id    BIGINT,
    emergency_priority   INTEGER      NOT NULL DEFAULT 1,
    relationship_label   VARCHAR(100),
    emergency_status     VARCHAR(50)  NOT NULL,
    tenant_id            VARCHAR(36)  NOT NULL,
    version              BIGINT       NOT NULL DEFAULT 0,
    created_at           TIMESTAMP,
    created_by           VARCHAR(100),
    last_modified_at     TIMESTAMP,
    last_modified_by     VARCHAR(100),
    CONSTRAINT fk_emergency_person         FOREIGN KEY (person_id)         REFERENCES pc_person(id),
    CONSTRAINT fk_emergency_contact_person FOREIGN KEY (contact_person_id) REFERENCES pc_person(id)
);

CREATE INDEX idx_emergency_contact_person   ON pc_emergency_contact (person_id);
CREATE INDEX idx_emergency_contact_priority ON pc_emergency_contact (emergency_priority, emergency_status);
CREATE INDEX idx_emergency_contact_tenant   ON pc_emergency_contact (tenant_id);

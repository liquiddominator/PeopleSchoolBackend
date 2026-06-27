CREATE TABLE pc_person_contact (
    id               BIGSERIAL    PRIMARY KEY,
    person_id        BIGINT       NOT NULL,
    tipo             VARCHAR(20)  NOT NULL,
    valor            VARCHAR(120) NOT NULL,
    uso              VARCHAR(20)  NOT NULL,
    es_principal     BOOLEAN      NOT NULL DEFAULT FALSE,
    tenant_id        VARCHAR(36)  NOT NULL,
    version          BIGINT       NOT NULL DEFAULT 0,
    created_at       TIMESTAMP,
    created_by       VARCHAR(100),
    last_modified_at TIMESTAMP,
    last_modified_by VARCHAR(100),
    CONSTRAINT fk_contact_person FOREIGN KEY (person_id) REFERENCES pc_person (id)
);

CREATE INDEX idx_contact_person_id ON pc_person_contact (person_id);
CREATE INDEX idx_contact_tenant_id ON pc_person_contact (tenant_id);

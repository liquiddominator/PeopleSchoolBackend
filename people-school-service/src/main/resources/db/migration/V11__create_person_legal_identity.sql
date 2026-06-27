CREATE TABLE pc_person_legal_identity (
    id               BIGSERIAL    PRIMARY KEY,
    person_id        BIGINT       NOT NULL,
    tipo             VARCHAR(20)  NOT NULL,
    numero           VARCHAR(30)  NOT NULL,
    pais_emisor      VARCHAR(60)  NOT NULL,
    es_principal     BOOLEAN      NOT NULL DEFAULT FALSE,
    estado           VARCHAR(20)  NOT NULL,
    tenant_id        VARCHAR(36)  NOT NULL,
    version          BIGINT       NOT NULL DEFAULT 0,
    created_at       TIMESTAMP,
    created_by       VARCHAR(100),
    last_modified_at TIMESTAMP,
    last_modified_by VARCHAR(100),
    CONSTRAINT fk_legal_identity_person FOREIGN KEY (person_id) REFERENCES pc_person (id)
);

CREATE INDEX idx_legal_identity_person_id ON pc_person_legal_identity (person_id);
CREATE INDEX idx_legal_identity_tenant_id ON pc_person_legal_identity (tenant_id);

CREATE TABLE pc_person_address (
    id               BIGSERIAL    PRIMARY KEY,
    person_id        BIGINT       NOT NULL,
    pais             VARCHAR(60)  NOT NULL,
    ciudad           VARCHAR(80)  NOT NULL,
    linea1           VARCHAR(200) NOT NULL,
    linea2           VARCHAR(200),
    es_principal     BOOLEAN      NOT NULL DEFAULT FALSE,
    tenant_id        VARCHAR(36)  NOT NULL,
    version          BIGINT       NOT NULL DEFAULT 0,
    created_at       TIMESTAMP,
    created_by       VARCHAR(100),
    last_modified_at TIMESTAMP,
    last_modified_by VARCHAR(100),
    CONSTRAINT fk_address_person FOREIGN KEY (person_id) REFERENCES pc_person (id)
);

CREATE INDEX idx_address_person_id ON pc_person_address (person_id);
CREATE INDEX idx_address_tenant_id ON pc_person_address (tenant_id);

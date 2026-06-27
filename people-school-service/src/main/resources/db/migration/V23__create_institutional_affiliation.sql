CREATE TABLE pc_institutional_affiliation (
    id                      BIGSERIAL    PRIMARY KEY,
    person_id               BIGINT       NOT NULL,
    affiliation_type_code   VARCHAR(50)  NOT NULL,
    site_id                 BIGINT,
    organizational_unit_id  BIGINT,
    affiliation_status      VARCHAR(50)  NOT NULL,
    effective_from          DATE,
    effective_to            DATE,
    tenant_id               VARCHAR(36)  NOT NULL,
    version                 BIGINT       NOT NULL DEFAULT 0,
    created_at              TIMESTAMP,
    created_by              VARCHAR(100),
    last_modified_at        TIMESTAMP,
    last_modified_by        VARCHAR(100),
    CONSTRAINT fk_affiliation_person FOREIGN KEY (person_id) REFERENCES pc_person(id)
);

CREATE INDEX idx_affiliation_person   ON pc_institutional_affiliation (person_id);
CREATE INDEX idx_affiliation_tenant   ON pc_institutional_affiliation (tenant_id);

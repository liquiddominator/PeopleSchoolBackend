CREATE TABLE pc_person_document_reference (
    id                              BIGSERIAL    PRIMARY KEY,
    person_id                       BIGINT       NOT NULL,
    document_reference_type_code    VARCHAR(50)  NOT NULL,
    asset_id                        BIGINT       NOT NULL,
    reference_status                VARCHAR(50)  NOT NULL,
    notes                           TEXT,
    tenant_id                       VARCHAR(36)  NOT NULL,
    version                         BIGINT       NOT NULL DEFAULT 0,
    created_at                      TIMESTAMP,
    created_by                      VARCHAR(100),
    last_modified_at                TIMESTAMP,
    last_modified_by                VARCHAR(100),
    CONSTRAINT fk_doc_ref_person FOREIGN KEY (person_id) REFERENCES pc_person(id)
);

CREATE INDEX idx_doc_ref_person ON pc_person_document_reference (person_id);
CREATE INDEX idx_doc_ref_tenant ON pc_person_document_reference (tenant_id);

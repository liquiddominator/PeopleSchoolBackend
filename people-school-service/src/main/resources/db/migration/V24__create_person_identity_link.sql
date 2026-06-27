CREATE TABLE pc_person_identity_link (
    id                      BIGSERIAL    PRIMARY KEY,
    person_id               BIGINT       NOT NULL,
    iam_subject_id          VARCHAR(150) NOT NULL,
    identity_provider_code  VARCHAR(50),
    identity_link_status    VARCHAR(50)  NOT NULL,
    linked_at               TIMESTAMP    NOT NULL,
    linked_by               VARCHAR(100),
    tenant_id               VARCHAR(36)  NOT NULL,
    version                 BIGINT       NOT NULL DEFAULT 0,
    created_at              TIMESTAMP,
    created_by              VARCHAR(100),
    last_modified_at        TIMESTAMP,
    last_modified_by        VARCHAR(100),
    CONSTRAINT fk_identity_link_person FOREIGN KEY (person_id) REFERENCES pc_person(id)
);

CREATE INDEX idx_identity_link_person ON pc_person_identity_link (person_id);
CREATE INDEX idx_identity_link_tenant ON pc_person_identity_link (tenant_id);

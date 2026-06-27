CREATE TABLE pc_financial_responsible_relationship (
    id                      BIGSERIAL    PRIMARY KEY,
    beneficiary_person_id   BIGINT       NOT NULL,
    responsible_person_id   BIGINT       NOT NULL,
    household_id            BIGINT,
    responsibility_status   VARCHAR(50)  NOT NULL,
    effective_from          DATE,
    effective_to            DATE,
    notes                   TEXT,
    tenant_id               VARCHAR(36)  NOT NULL,
    version                 BIGINT       NOT NULL DEFAULT 0,
    created_at              TIMESTAMP,
    created_by              VARCHAR(100),
    last_modified_at        TIMESTAMP,
    last_modified_by        VARCHAR(100),
    CONSTRAINT fk_fin_resp_beneficiary  FOREIGN KEY (beneficiary_person_id)  REFERENCES pc_person(id),
    CONSTRAINT fk_fin_resp_responsible  FOREIGN KEY (responsible_person_id)  REFERENCES pc_person(id),
    CONSTRAINT fk_fin_resp_household    FOREIGN KEY (household_id)           REFERENCES pc_household(id)
);

CREATE INDEX idx_fin_resp_beneficiary ON pc_financial_responsible_relationship (beneficiary_person_id);
CREATE INDEX idx_fin_resp_responsible ON pc_financial_responsible_relationship (responsible_person_id);
CREATE INDEX idx_fin_resp_tenant      ON pc_financial_responsible_relationship (tenant_id);

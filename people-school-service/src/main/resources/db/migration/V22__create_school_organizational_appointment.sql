CREATE TABLE pc_school_organizational_appointment (
    id                          BIGSERIAL    PRIMARY KEY,
    person_id                   BIGINT       NOT NULL,
    appointment_code            VARCHAR(50)  NOT NULL,
    organizational_unit_id      BIGINT,
    appointment_status          VARCHAR(50)  NOT NULL,
    started_at                  TIMESTAMP    NOT NULL,
    ended_at                    TIMESTAMP,
    tenant_id                   VARCHAR(36)  NOT NULL,
    version                     BIGINT       NOT NULL DEFAULT 0,
    created_at                  TIMESTAMP,
    created_by                  VARCHAR(100),
    last_modified_at            TIMESTAMP,
    last_modified_by            VARCHAR(100),
    CONSTRAINT fk_appointment_person  FOREIGN KEY (person_id) REFERENCES pc_person(id)
);

CREATE INDEX idx_appointment_person    ON pc_school_organizational_appointment (person_id);
CREATE INDEX idx_appointment_code      ON pc_school_organizational_appointment (appointment_code, appointment_status);
CREATE INDEX idx_appointment_tenant    ON pc_school_organizational_appointment (tenant_id);

CREATE TABLE pc_household_membership (
    id                       BIGSERIAL PRIMARY KEY,
    household_id             BIGINT      NOT NULL REFERENCES pc_household(id),
    person_id                BIGINT      NOT NULL REFERENCES pc_person(id),
    membership_role_code     VARCHAR(30) NOT NULL,
    membership_status        VARCHAR(20) NOT NULL,
    effective_from           DATE        NOT NULL,
    effective_to             DATE,
    is_primary_guardian_group BOOLEAN    NOT NULL DEFAULT FALSE,
    tenant_id                VARCHAR(36) NOT NULL,
    version                  BIGINT      NOT NULL DEFAULT 0,
    created_at               TIMESTAMP,
    created_by               VARCHAR(100),
    last_modified_at         TIMESTAMP,
    last_modified_by         VARCHAR(100)
);

-- Partial unique index: only one ACTIVE membership per person per household
CREATE UNIQUE INDEX uq_active_membership
    ON pc_household_membership (household_id, person_id)
    WHERE membership_status = 'ACTIVE';

CREATE INDEX idx_membership_household  ON pc_household_membership (household_id);
CREATE INDEX idx_membership_person     ON pc_household_membership (person_id);
CREATE INDEX idx_membership_tenant     ON pc_household_membership (tenant_id);
CREATE INDEX idx_membership_status     ON pc_household_membership (membership_status);

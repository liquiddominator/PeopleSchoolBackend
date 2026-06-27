CREATE UNIQUE INDEX uq_person_legal_identity_document
    ON pc_person_legal_identity (tenant_id, tipo, numero);

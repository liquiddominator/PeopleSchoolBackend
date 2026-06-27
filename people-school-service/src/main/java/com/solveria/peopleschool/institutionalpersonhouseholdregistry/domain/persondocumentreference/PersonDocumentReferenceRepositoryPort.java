package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.persondocumentreference;

import java.util.List;

public interface PersonDocumentReferenceRepositoryPort {
    List<PersonDocumentReference> findAllByPersonIdAndTenantId(Long personId, String tenantId);

    PersonDocumentReference save(PersonDocumentReference reference);
}

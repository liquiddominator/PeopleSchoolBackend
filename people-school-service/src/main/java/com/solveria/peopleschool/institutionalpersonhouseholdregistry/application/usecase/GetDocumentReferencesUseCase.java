package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.persondocumentreference.PersonDocumentReference;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.persondocumentreference.PersonDocumentReferenceRepositoryPort;
import java.util.List;

public class GetDocumentReferencesUseCase {

    private final PersonDocumentReferenceRepositoryPort repository;

    public GetDocumentReferencesUseCase(PersonDocumentReferenceRepositoryPort repository) {
        this.repository = repository;
    }

    public List<PersonDocumentReference> execute(Long personId, String tenantId) {
        return repository.findAllByPersonIdAndTenantId(personId, tenantId);
    }
}

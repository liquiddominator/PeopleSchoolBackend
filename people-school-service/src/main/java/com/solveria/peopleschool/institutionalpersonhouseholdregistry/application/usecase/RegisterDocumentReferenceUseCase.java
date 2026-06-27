package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreateDocumentReferenceRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.persondocumentreference.DocumentReferenceStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.persondocumentreference.PersonDocumentReference;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.persondocumentreference.PersonDocumentReferenceRepositoryPort;

public class RegisterDocumentReferenceUseCase {

    private final PersonDocumentReferenceRepositoryPort repository;

    public RegisterDocumentReferenceUseCase(PersonDocumentReferenceRepositoryPort repository) {
        this.repository = repository;
    }

    public PersonDocumentReference execute(
            CreateDocumentReferenceRequest request, String tenantId) {
        PersonDocumentReference reference =
                new PersonDocumentReference(
                        null,
                        request.personId(),
                        request.documentReferenceTypeCode(),
                        request.assetId(),
                        DocumentReferenceStatus.valueOf(request.referenceStatus()),
                        request.notes(),
                        tenantId,
                        null,
                        null,
                        null,
                        null,
                        null);
        return repository.save(reference);
    }
}

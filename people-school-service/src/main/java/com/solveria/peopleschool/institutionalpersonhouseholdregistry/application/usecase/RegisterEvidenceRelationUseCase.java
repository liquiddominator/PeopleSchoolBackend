package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreateEvidenceRelationRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personevidencerelation.EvidenceStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personevidencerelation.PersonEvidenceRelation;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personevidencerelation.PersonEvidenceRelationRepositoryPort;

public class RegisterEvidenceRelationUseCase {

    private final PersonEvidenceRelationRepositoryPort repository;

    public RegisterEvidenceRelationUseCase(PersonEvidenceRelationRepositoryPort repository) {
        this.repository = repository;
    }

    public PersonEvidenceRelation execute(CreateEvidenceRelationRequest request, String tenantId) {
        PersonEvidenceRelation relation =
                new PersonEvidenceRelation(
                        null,
                        request.personId(),
                        request.householdId(),
                        request.relatedContextType(),
                        request.relatedContextRefId(),
                        request.evidenceRoleCode(),
                        request.assetId(),
                        EvidenceStatus.valueOf(request.evidenceStatus()),
                        tenantId,
                        null,
                        null,
                        null,
                        null,
                        null);
        return repository.save(relation);
    }
}

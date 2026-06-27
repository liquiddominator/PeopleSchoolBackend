package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personevidencerelation.PersonEvidenceRelation;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personevidencerelation.PersonEvidenceRelationRepositoryPort;
import java.util.List;

public class GetEvidenceRelationsUseCase {

    private final PersonEvidenceRelationRepositoryPort repository;

    public GetEvidenceRelationsUseCase(PersonEvidenceRelationRepositoryPort repository) {
        this.repository = repository;
    }

    public List<PersonEvidenceRelation> execute(Long personId, String tenantId) {
        return repository.findAllByPersonIdAndTenantId(personId, tenantId);
    }
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personevidencerelation;

import java.util.List;

public interface PersonEvidenceRelationRepositoryPort {
    List<PersonEvidenceRelation> findAllByPersonIdAndTenantId(Long personId, String tenantId);

    PersonEvidenceRelation save(PersonEvidenceRelation relation);
}

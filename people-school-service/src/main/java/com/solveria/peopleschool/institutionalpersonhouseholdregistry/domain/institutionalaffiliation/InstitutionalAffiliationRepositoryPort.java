package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalaffiliation;

import java.util.List;

public interface InstitutionalAffiliationRepositoryPort {
    List<InstitutionalAffiliation> findAllByPersonIdAndTenantId(Long personId, String tenantId);

    InstitutionalAffiliation save(InstitutionalAffiliation affiliation);
}

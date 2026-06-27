package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personidentitylink;

import java.util.List;

public interface PersonIdentityLinkRepositoryPort {
    List<PersonIdentityLink> findAllByPersonIdAndTenantId(Long personId, String tenantId);

    PersonIdentityLink save(PersonIdentityLink link);
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.legalidentity;

import java.util.List;
import java.util.Optional;

public interface PersonLegalIdentityRepositoryPort {
    List<PersonLegalIdentity> findByPersonIdAndTenantId(Long personId, String tenantId);

    Optional<PersonLegalIdentity> findById(Long id);

    boolean existsByDocumentInTenant(DocumentType tipo, String numero, String tenantId);

    boolean existsByDocumentInTenantExcludingIdentityId(
            DocumentType tipo, String numero, String tenantId, Long identityId);

    PersonLegalIdentity save(PersonLegalIdentity domain);

    void deleteById(Long id);
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.legalidentity.DocumentType;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.legalidentity.PersonLegalIdentity;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.legalidentity.PersonLegalIdentityRepositoryPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonLegalIdentityPersistenceAdapter implements PersonLegalIdentityRepositoryPort {

    private final PersonLegalIdentityJpaRepository repository;
    private final PersonLegalIdentityMapper mapper;

    @Override
    public List<PersonLegalIdentity> findByPersonIdAndTenantId(Long personId, String tenantId) {
        return repository.findByPersonIdAndTenantId(personId, tenantId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<PersonLegalIdentity> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public boolean existsByDocumentInTenant(DocumentType tipo, String numero, String tenantId) {
        return repository.existsByTipoAndNumeroAndTenantId(tipo.name(), numero, tenantId);
    }

    @Override
    public boolean existsByDocumentInTenantExcludingIdentityId(
            DocumentType tipo, String numero, String tenantId, Long identityId) {
        return repository.existsByTipoAndNumeroAndTenantIdAndIdNot(
                tipo.name(), numero, tenantId, identityId);
    }

    @Override
    public PersonLegalIdentity save(PersonLegalIdentity domain) {
        PersonLegalIdentityJpaEntity entity;
        if (domain.id() != null) {
            entity =
                    repository
                            .findById(domain.id())
                            .orElseThrow(
                                    () ->
                                            new EntityNotFoundException(
                                                    "PersonLegalIdentity", domain.id().toString()));
            mapper.updateEntity(entity, domain);
        } else {
            entity = mapper.toNewEntity(domain);
        }
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

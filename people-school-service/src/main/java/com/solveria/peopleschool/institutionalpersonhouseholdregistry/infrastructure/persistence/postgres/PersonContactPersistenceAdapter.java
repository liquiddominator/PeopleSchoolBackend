package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.contact.PersonContact;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.contact.PersonContactRepositoryPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonContactPersistenceAdapter implements PersonContactRepositoryPort {

    private final PersonContactJpaRepository repository;
    private final PersonContactMapper mapper;

    @Override
    public List<PersonContact> findByPersonIdAndTenantId(Long personId, String tenantId) {
        return repository.findByPersonIdAndTenantId(personId, tenantId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<PersonContact> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public PersonContact save(PersonContact domain) {
        PersonContactJpaEntity entity;
        if (domain.id() != null) {
            entity =
                    repository
                            .findById(domain.id())
                            .orElseThrow(
                                    () ->
                                            new EntityNotFoundException(
                                                    "PersonContact", domain.id().toString()));
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

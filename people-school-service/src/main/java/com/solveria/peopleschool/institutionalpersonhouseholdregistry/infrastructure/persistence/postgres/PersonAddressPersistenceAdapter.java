package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.address.PersonAddress;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.address.PersonAddressRepositoryPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonAddressPersistenceAdapter implements PersonAddressRepositoryPort {

    private final PersonAddressJpaRepository repository;
    private final PersonAddressMapper mapper;

    @Override
    public List<PersonAddress> findByPersonIdAndTenantId(Long personId, String tenantId) {
        return repository.findByPersonIdAndTenantId(personId, tenantId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<PersonAddress> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public PersonAddress save(PersonAddress domain) {
        PersonAddressJpaEntity entity;
        if (domain.id() != null) {
            entity =
                    repository
                            .findById(domain.id())
                            .orElseThrow(
                                    () ->
                                            new EntityNotFoundException(
                                                    "PersonAddress", domain.id().toString()));
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

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitiveprofile.PersonSensitiveProfile;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitiveprofile.PersonSensitiveProfileRepositoryPort;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonSensitiveProfilePersistenceAdapter
        implements PersonSensitiveProfileRepositoryPort {

    private final PersonSensitiveProfileJpaRepository repository;
    private final PersonSensitiveProfileMapper mapper;

    @Override
    public Optional<PersonSensitiveProfile> findByPersonIdAndTenantId(
            Long personId, String tenantId) {
        return repository.findByPersonIdAndTenantId(personId, tenantId).map(mapper::toDomain);
    }

    @Override
    public PersonSensitiveProfile save(PersonSensitiveProfile profile) {
        PersonSensitiveProfileJpaEntity entity;
        if (profile.id() != null) {
            entity = repository.findById(profile.id()).orElseThrow();
            mapper.updateEntity(entity, profile);
        } else {
            entity = mapper.toNewEntity(profile);
        }
        return mapper.toDomain(repository.save(entity));
    }
}

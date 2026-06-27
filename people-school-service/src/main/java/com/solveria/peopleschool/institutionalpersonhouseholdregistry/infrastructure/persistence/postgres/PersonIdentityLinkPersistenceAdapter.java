package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personidentitylink.PersonIdentityLink;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personidentitylink.PersonIdentityLinkRepositoryPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonIdentityLinkPersistenceAdapter implements PersonIdentityLinkRepositoryPort {

    private final PersonIdentityLinkJpaRepository repository;
    private final PersonIdentityLinkMapper mapper;

    @Override
    public List<PersonIdentityLink> findAllByPersonIdAndTenantId(Long personId, String tenantId) {
        return repository.findAllByPersonIdAndTenantId(personId, tenantId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public PersonIdentityLink save(PersonIdentityLink link) {
        PersonIdentityLinkJpaEntity entity;
        if (link.id() != null) {
            entity = repository.findById(link.id()).orElseThrow();
        } else {
            entity = mapper.toNewEntity(link);
        }
        return mapper.toDomain(repository.save(entity));
    }
}

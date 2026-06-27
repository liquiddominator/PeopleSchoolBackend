package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.conflict.ConflictStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.conflict.PersonConflict;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.conflict.PersonConflictRepositoryPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonConflictPersistenceAdapter implements PersonConflictRepositoryPort {

    private final PersonConflictJpaRepository repository;
    private final PersonConflictMapper mapper;

    @Override
    public List<PersonConflict> findByTenantId(String tenantId) {
        return repository.findByTenantId(tenantId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<PersonConflict> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public long countByTenantIdAndConflictStatusIn(String tenantId, List<ConflictStatus> statuses) {
        List<String> statusCodes = statuses.stream().map(Enum::name).toList();
        return repository.countByTenantIdAndConflictStatusIn(tenantId, statusCodes);
    }

    @Override
    public PersonConflict save(PersonConflict domain) {
        PersonConflictJpaEntity entity;
        if (domain.id() != null) {
            entity =
                    repository
                            .findById(domain.id())
                            .orElseThrow(
                                    () ->
                                            new EntityNotFoundException(
                                                    "PersonConflict", domain.id().toString()));
            mapper.updateEntity(entity, domain);
        } else {
            entity = mapper.toNewEntity(domain);
        }
        return mapper.toDomain(repository.save(entity));
    }
}

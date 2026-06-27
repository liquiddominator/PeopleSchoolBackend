package com.solveria.peopleschool.schoolregulationsregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.schoolregulationsregistry.domain.schoolregulation.SchoolRegulation;
import com.solveria.peopleschool.schoolregulationsregistry.domain.schoolregulation.SchoolRegulationRepositoryPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchoolRegulationPersistenceAdapter implements SchoolRegulationRepositoryPort {

    private final SchoolRegulationJpaRepository repository;
    private final SchoolRegulationMapper mapper;

    @Override
    public SchoolRegulation save(SchoolRegulation domain) {
        SchoolRegulationJpaEntity entity;
        if (domain.id() != null) {
            entity =
                    repository
                            .findById(domain.id())
                            .orElseThrow(
                                    () ->
                                            new EntityNotFoundException(
                                                    "SchoolRegulation", domain.id().toString()));
            mapper.updateEntity(entity, domain);
        } else {
            entity = mapper.toNewEntity(domain);
        }
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<SchoolRegulation> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<SchoolRegulation> findAllByTenantId(String tenantId) {
        return repository.findAllByTenantIdOrderByRegulationCodeAsc(tenantId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsByRegulationCodeAndTenantId(String code, String tenantId) {
        return repository.existsByRegulationCodeAndTenantId(code, tenantId);
    }
}

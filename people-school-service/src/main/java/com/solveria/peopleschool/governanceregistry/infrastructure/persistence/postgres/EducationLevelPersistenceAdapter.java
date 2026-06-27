package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.governanceregistry.domain.educationlevel.EducationLevel;
import com.solveria.peopleschool.governanceregistry.domain.educationlevel.EducationLevelRepositoryPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EducationLevelPersistenceAdapter implements EducationLevelRepositoryPort {

    private final EducationLevelJpaRepository repository;
    private final EducationLevelMapper mapper;

    @Override
    public EducationLevel save(EducationLevel domain) {
        EducationLevelJpaEntity entity;
        if (domain.id() != null) {
            entity =
                    repository
                            .findById(domain.id())
                            .orElseThrow(
                                    () ->
                                            new EntityNotFoundException(
                                                    "EducationLevel", domain.id().toString()));
            mapper.updateEntity(entity, domain);
        } else {
            entity = mapper.toNewEntity(domain);
        }
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<EducationLevel> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<EducationLevel> findAllByTenantId(String tenantId) {
        return repository.findAllByTenantIdOrderByLevelSequenceAsc(tenantId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsByLevelCodeAndTenantId(String code, String tenantId) {
        return repository.existsByLevelCodeAndTenantId(code, tenantId);
    }
}

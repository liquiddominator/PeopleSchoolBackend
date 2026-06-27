package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnit;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnitRepositoryPort;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EducationUnitPersistenceAdapter implements EducationUnitRepositoryPort {

    private final EducationUnitJpaRepository repository;
    private final EducationUnitMapper mapper;

    @Override
    public EducationUnit save(EducationUnit domain) {
        EducationUnitJpaEntity entity;
        if (domain.id() != null) {
            entity =
                    repository
                            .findById(domain.id())
                            .orElseThrow(
                                    () ->
                                            new EntityNotFoundException(
                                                    "EducationUnit", domain.id().toString()));
            mapper.updateEntity(entity, domain);
        } else {
            entity = mapper.toNewEntity(domain);
        }
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<EducationUnit> findByTenantId(String tenantId) {
        return repository.findByTenantId(tenantId).map(mapper::toDomain);
    }

    @Override
    public Optional<EducationUnit> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public boolean existsByUnitCodeAndTenantId(String unitCode, String tenantId) {
        return repository.existsByUnitCodeAndTenantId(unitCode, tenantId);
    }
}

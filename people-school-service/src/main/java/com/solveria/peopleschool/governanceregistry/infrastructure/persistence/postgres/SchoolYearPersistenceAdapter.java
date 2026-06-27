package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.governanceregistry.domain.schoolyear.SchoolYear;
import com.solveria.peopleschool.governanceregistry.domain.schoolyear.SchoolYearRepositoryPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchoolYearPersistenceAdapter implements SchoolYearRepositoryPort {

    private final SchoolYearJpaRepository repository;
    private final SchoolYearMapper mapper;

    @Override
    public SchoolYear save(SchoolYear domain) {
        SchoolYearJpaEntity entity;
        if (domain.id() != null) {
            entity =
                    repository
                            .findById(domain.id())
                            .orElseThrow(
                                    () ->
                                            new EntityNotFoundException(
                                                    "SchoolYear", domain.id().toString()));
            mapper.updateEntity(entity, domain);
        } else {
            entity = mapper.toNewEntity(domain);
        }
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<SchoolYear> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<SchoolYear> findAllByTenantId(String tenantId) {
        return repository.findAllByTenantId(tenantId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public void clearCurrentDefaultForTenant(String tenantId) {
        repository.clearCurrentDefaultForTenant(tenantId);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsBySchoolYearCodeAndTenantId(String code, String tenantId) {
        return repository.existsBySchoolYearCodeAndTenantId(code, tenantId);
    }
}

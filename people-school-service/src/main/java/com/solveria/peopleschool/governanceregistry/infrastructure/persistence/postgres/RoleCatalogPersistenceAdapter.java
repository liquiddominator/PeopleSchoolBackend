package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.governanceregistry.domain.rolecatalog.InstitutionalRoleCatalog;
import com.solveria.peopleschool.governanceregistry.domain.rolecatalog.RoleCatalogRepositoryPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleCatalogPersistenceAdapter implements RoleCatalogRepositoryPort {

    private final RoleCatalogJpaRepository repository;
    private final RoleCatalogMapper mapper;

    @Override
    public InstitutionalRoleCatalog save(InstitutionalRoleCatalog domain) {
        RoleCatalogJpaEntity entity;
        if (domain.id() != null) {
            entity =
                    repository
                            .findById(domain.id())
                            .orElseThrow(
                                    () ->
                                            new EntityNotFoundException(
                                                    "InstitutionalRoleCatalog",
                                                    domain.id().toString()));
            mapper.updateEntity(entity, domain);
        } else {
            entity = mapper.toNewEntity(domain);
        }
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<InstitutionalRoleCatalog> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<InstitutionalRoleCatalog> findAllByTenantId(String tenantId) {
        return repository.findAllByTenantIdOrderByDisplayOrderAsc(tenantId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsByRoleCodeAndTenantId(String code, String tenantId) {
        return repository.existsByRoleCodeAndTenantId(code, tenantId);
    }
}

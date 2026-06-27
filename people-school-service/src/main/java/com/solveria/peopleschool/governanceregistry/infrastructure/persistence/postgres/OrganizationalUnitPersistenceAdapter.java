package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.governanceregistry.domain.orgunit.OrganizationalUnit;
import com.solveria.peopleschool.governanceregistry.domain.orgunit.OrganizationalUnitRepositoryPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrganizationalUnitPersistenceAdapter implements OrganizationalUnitRepositoryPort {

    private final OrganizationalUnitJpaRepository repository;
    private final OrganizationalUnitMapper mapper;

    @Override
    public OrganizationalUnit save(OrganizationalUnit domain) {
        OrganizationalUnitJpaEntity entity;
        if (domain.id() != null) {
            entity =
                    repository
                            .findById(domain.id())
                            .orElseThrow(
                                    () ->
                                            new EntityNotFoundException(
                                                    "OrganizationalUnit", domain.id().toString()));
            mapper.updateEntity(entity, domain);
        } else {
            entity = mapper.toNewEntity(domain);
        }
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<OrganizationalUnit> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<OrganizationalUnit> findAllByTenantId(String tenantId) {
        return repository.findAllByTenantIdOrderByDisplayOrderAsc(tenantId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsByOrgUnitCodeAndTenantId(String code, String tenantId) {
        return repository.existsByOrgUnitCodeAndTenantId(code, tenantId);
    }
}

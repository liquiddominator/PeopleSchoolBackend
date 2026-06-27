package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.governanceregistry.domain.orggroup.OrgGroup;
import com.solveria.peopleschool.governanceregistry.domain.orggroup.OrgGroupRepositoryPort;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrgGroupPersistenceAdapter implements OrgGroupRepositoryPort {

    private final OrgGroupJpaRepository repository;
    private final OrgGroupMapper mapper;

    @Override
    public OrgGroup save(OrgGroup domain) {
        OrgGroupJpaEntity entity;
        if (domain.id() != null) {
            entity =
                    repository
                            .findById(domain.id())
                            .orElseThrow(
                                    () ->
                                            new EntityNotFoundException(
                                                    "OrgGroup", domain.id().toString()));
            mapper.updateEntity(entity, domain);
        } else {
            entity = mapper.toNewEntity(domain);
        }
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<OrgGroup> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<OrgGroup> findByTenantId(String tenantId) {
        return repository.findByTenantId(tenantId).map(mapper::toDomain);
    }

    @Override
    public boolean existsByTenantCodeAndTenantId(String tenantCode, String tenantId) {
        return repository.existsByTenantCodeAndTenantId(tenantCode, tenantId);
    }
}

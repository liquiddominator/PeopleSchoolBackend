package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.governanceregistry.domain.unitsite.UnitSite;
import com.solveria.peopleschool.governanceregistry.domain.unitsite.UnitSiteRepositoryPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UnitSitePersistenceAdapter implements UnitSiteRepositoryPort {

    private final UnitSiteJpaRepository repository;
    private final UnitSiteMapper mapper;

    @Override
    public UnitSite save(UnitSite domain) {
        UnitSiteJpaEntity entity;
        if (domain.id() != null) {
            entity =
                    repository
                            .findById(domain.id())
                            .orElseThrow(
                                    () ->
                                            new EntityNotFoundException(
                                                    "UnitSite", domain.id().toString()));
            mapper.updateEntity(entity, domain);
        } else {
            entity = mapper.toNewEntity(domain);
        }
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<UnitSite> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<UnitSite> findAllByTenantId(String tenantId) {
        return repository.findAllByTenantIdOrderByIsMainSiteDescSiteNameAsc(tenantId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsBySiteCodeAndTenantId(String siteCode, String tenantId) {
        return repository.existsBySiteCodeAndTenantId(siteCode, tenantId);
    }

    @Override
    public void clearMainSiteForTenant(String tenantId) {
        repository.clearMainSiteForTenant(tenantId);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

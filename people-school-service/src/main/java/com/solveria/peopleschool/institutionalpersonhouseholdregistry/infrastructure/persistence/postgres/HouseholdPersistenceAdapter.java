package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.Household;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.HouseholdRepositoryPort;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.HouseholdStatus;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HouseholdPersistenceAdapter implements HouseholdRepositoryPort {

    private final HouseholdJpaRepository repository;
    private final HouseholdMapper mapper;

    @Override
    public Household save(Household domain) {
        HouseholdJpaEntity entity;
        if (domain.id() != null) {
            entity =
                    repository
                            .findById(domain.id())
                            .orElseThrow(
                                    () ->
                                            new EntityNotFoundException(
                                                    "Household", domain.id().toString()));
            mapper.updateEntity(entity, domain);
        } else {
            entity = mapper.toNewEntity(domain);
        }
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Household> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public boolean existsByHouseholdCodeAndTenantId(String householdCode, String tenantId) {
        return repository.existsByHouseholdCodeAndTenantId(householdCode, tenantId);
    }

    @Override
    public Page<Household> findAll(
            String tenantId, String householdTypeCode, String householdStatus, Pageable pageable) {
        Specification<HouseholdJpaEntity> spec =
                Specification.where(equalsTenantId(tenantId))
                        .and(equalsHouseholdTypeCode(householdTypeCode))
                        .and(equalsHouseholdStatus(householdStatus));
        return repository.findAll(spec, pageable).map(mapper::toDomain);
    }

    @Override
    public long countByTenantIdAndHouseholdStatus(
            String tenantId, HouseholdStatus householdStatus) {
        return repository.countByTenantIdAndHouseholdStatus(tenantId, householdStatus.name());
    }

    private Specification<HouseholdJpaEntity> equalsTenantId(String tenantId) {
        return (root, query, cb) -> cb.equal(root.get("tenantId"), tenantId);
    }

    private Specification<HouseholdJpaEntity> equalsHouseholdTypeCode(String value) {
        return value == null
                ? null
                : (root, query, cb) -> cb.equal(root.get("householdTypeCode"), value);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private Specification<HouseholdJpaEntity> equalsHouseholdStatus(String value) {
        return value == null
                ? null
                : (root, query, cb) -> cb.equal(root.get("householdStatus"), value);
    }
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.householdmembership.HouseholdMembership;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.householdmembership.HouseholdMembershipRepositoryPort;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HouseholdMembershipPersistenceAdapter implements HouseholdMembershipRepositoryPort {

    private final HouseholdMembershipJpaRepository repository;
    private final HouseholdMembershipMapper mapper;

    @Override
    public HouseholdMembership save(HouseholdMembership domain) {
        HouseholdMembershipJpaEntity entity;
        if (domain.id() != null) {
            entity = repository.findById(domain.id()).orElseThrow();
            mapper.updateEntity(entity, domain);
        } else {
            entity = mapper.toNewEntity(domain);
        }
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<HouseholdMembership> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<HouseholdMembership> findByHouseholdId(
            Long householdId, String tenantId, Pageable pageable) {
        return repository
                .findByHouseholdIdAndTenantId(householdId, tenantId, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsActiveByHouseholdIdAndPersonId(Long householdId, Long personId) {
        return repository.existsActiveByHouseholdIdAndPersonId(householdId, personId);
    }
}

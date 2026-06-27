package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.pickupauthorization.PickupAuthorization;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.pickupauthorization.PickupAuthorizationRepositoryPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PickupAuthorizationPersistenceAdapter implements PickupAuthorizationRepositoryPort {

    private final PickupAuthorizationJpaRepository repository;
    private final PickupAuthorizationMapper mapper;

    @Override
    public PickupAuthorization save(PickupAuthorization domain) {
        PickupAuthorizationJpaEntity entity;
        if (domain.id() != null) {
            entity = repository.findById(domain.id()).orElseThrow();
        } else {
            entity = mapper.toNewEntity(domain);
        }
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<PickupAuthorization> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<PickupAuthorization> findAllByStudentPersonIdAndTenantId(
            Long studentPersonId, String tenantId) {
        return repository.findAllByStudentPersonIdAndTenantId(studentPersonId, tenantId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

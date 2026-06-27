package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.emergencycontact.EmergencyContact;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.emergencycontact.EmergencyContactRepositoryPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmergencyContactPersistenceAdapter implements EmergencyContactRepositoryPort {

    private final EmergencyContactJpaRepository repository;
    private final EmergencyContactMapper mapper;

    @Override
    public EmergencyContact save(EmergencyContact domain) {
        EmergencyContactJpaEntity entity;
        if (domain.id() != null) {
            entity = repository.findById(domain.id()).orElseThrow();
        } else {
            entity = mapper.toNewEntity(domain);
        }
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<EmergencyContact> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<EmergencyContact> findAllByPersonIdAndTenantId(Long personId, String tenantId) {
        return repository
                .findAllByPersonIdAndTenantIdOrderByEmergencyPriorityAsc(personId, tenantId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.guardianrelationship.GuardianRelationship;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.guardianrelationship.GuardianRelationshipRepositoryPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GuardianRelationshipPersistenceAdapter implements GuardianRelationshipRepositoryPort {

    private final GuardianRelationshipJpaRepository repository;
    private final GuardianRelationshipMapper mapper;

    @Override
    public GuardianRelationship save(GuardianRelationship domain) {
        GuardianRelationshipJpaEntity entity;
        if (domain.id() != null) {
            entity = repository.findById(domain.id()).orElseThrow();
        } else {
            entity = mapper.toNewEntity(domain);
        }
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<GuardianRelationship> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<GuardianRelationship> findAllByStudentPersonIdAndTenantId(
            Long studentPersonId, String tenantId) {
        return repository.findAllByStudentPersonIdAndTenantId(studentPersonId, tenantId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<GuardianRelationship> findAllByGuardianPersonIdAndTenantId(
            Long guardianPersonId, String tenantId) {
        return repository.findAllByGuardianPersonIdAndTenantId(guardianPersonId, tenantId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public long countByTenantId(String tenantId) {
        return repository.countByTenantId(tenantId);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

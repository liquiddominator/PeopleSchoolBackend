package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalroleassignment.InstitutionalRoleAssignment;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalroleassignment.InstitutionalRoleAssignmentRepositoryPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstitutionalRoleAssignmentPersistenceAdapter
        implements InstitutionalRoleAssignmentRepositoryPort {

    private final InstitutionalRoleAssignmentJpaRepository repository;
    private final InstitutionalRoleAssignmentMapper mapper;

    @Override
    public InstitutionalRoleAssignment save(InstitutionalRoleAssignment domain) {
        InstitutionalRoleAssignmentJpaEntity entity;
        if (domain.id() != null) {
            entity = repository.findById(domain.id()).orElseThrow();
        } else {
            entity = mapper.toNewEntity(domain);
        }
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<InstitutionalRoleAssignment> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<InstitutionalRoleAssignment> findAllByPersonIdAndTenantId(
            Long personId, String tenantId) {
        return repository.findAllByPersonIdAndTenantId(personId, tenantId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByPersonIdAndRoleCodeAndTenantId(
            Long personId, String roleCode, String tenantId) {
        return repository.existsByPersonIdAndRoleCodeAndTenantId(personId, roleCode, tenantId);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

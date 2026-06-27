package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.financialresponsiblerelationship.FinancialResponsibleRelationship;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.financialresponsiblerelationship.FinancialResponsibleRepositoryPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FinancialResponsiblePersistenceAdapter implements FinancialResponsibleRepositoryPort {

    private final FinancialResponsibleJpaRepository repository;
    private final FinancialResponsibleMapper mapper;

    @Override
    public FinancialResponsibleRelationship save(FinancialResponsibleRelationship domain) {
        FinancialResponsibleJpaEntity entity;
        if (domain.id() != null) {
            entity = repository.findById(domain.id()).orElseThrow();
        } else {
            entity = mapper.toNewEntity(domain);
        }
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<FinancialResponsibleRelationship> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<FinancialResponsibleRelationship> findAllByBeneficiaryPersonIdAndTenantId(
            Long beneficiaryPersonId, String tenantId) {
        return repository
                .findAllByBeneficiaryPersonIdAndTenantId(beneficiaryPersonId, tenantId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

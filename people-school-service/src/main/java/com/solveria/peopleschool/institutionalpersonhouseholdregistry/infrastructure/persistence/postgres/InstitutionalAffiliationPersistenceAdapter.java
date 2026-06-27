package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalaffiliation.InstitutionalAffiliation;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalaffiliation.InstitutionalAffiliationRepositoryPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstitutionalAffiliationPersistenceAdapter
        implements InstitutionalAffiliationRepositoryPort {

    private final InstitutionalAffiliationJpaRepository repository;
    private final InstitutionalAffiliationMapper mapper;

    @Override
    public List<InstitutionalAffiliation> findAllByPersonIdAndTenantId(
            Long personId, String tenantId) {
        return repository.findAllByPersonIdAndTenantId(personId, tenantId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public InstitutionalAffiliation save(InstitutionalAffiliation affiliation) {
        InstitutionalAffiliationJpaEntity entity;
        if (affiliation.id() != null) {
            entity = repository.findById(affiliation.id()).orElseThrow();
        } else {
            entity = mapper.toNewEntity(affiliation);
        }
        return mapper.toDomain(repository.save(entity));
    }
}

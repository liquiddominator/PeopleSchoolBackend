package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personevidencerelation.PersonEvidenceRelation;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personevidencerelation.PersonEvidenceRelationRepositoryPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonEvidenceRelationPersistenceAdapter
        implements PersonEvidenceRelationRepositoryPort {

    private final PersonEvidenceRelationJpaRepository repository;
    private final PersonEvidenceRelationMapper mapper;

    @Override
    public List<PersonEvidenceRelation> findAllByPersonIdAndTenantId(
            Long personId, String tenantId) {
        return repository.findAllByPersonIdAndTenantId(personId, tenantId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public PersonEvidenceRelation save(PersonEvidenceRelation relation) {
        PersonEvidenceRelationJpaEntity entity;
        if (relation.id() != null) {
            entity = repository.findById(relation.id()).orElseThrow();
        } else {
            entity = mapper.toNewEntity(relation);
        }
        return mapper.toDomain(repository.save(entity));
    }
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.persondocumentreference.PersonDocumentReference;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.persondocumentreference.PersonDocumentReferenceRepositoryPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonDocumentReferencePersistenceAdapter
        implements PersonDocumentReferenceRepositoryPort {

    private final PersonDocumentReferenceJpaRepository repository;
    private final PersonDocumentReferenceMapper mapper;

    @Override
    public List<PersonDocumentReference> findAllByPersonIdAndTenantId(
            Long personId, String tenantId) {
        return repository.findAllByPersonIdAndTenantId(personId, tenantId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public PersonDocumentReference save(PersonDocumentReference reference) {
        PersonDocumentReferenceJpaEntity entity;
        if (reference.id() != null) {
            entity = repository.findById(reference.id()).orElseThrow();
        } else {
            entity = mapper.toNewEntity(reference);
        }
        return mapper.toDomain(repository.save(entity));
    }
}

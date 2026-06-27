package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.CoreStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.Person;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.PersonRepositoryPort;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonPersistenceAdapter implements PersonRepositoryPort {

    private final PersonJpaRepository repository;
    private final PersonMapper mapper;

    @Override
    public Person save(Person domain) {
        PersonJpaEntity entity;
        if (domain.id() != null) {
            entity =
                    repository
                            .findById(domain.id())
                            .orElseThrow(
                                    () ->
                                            new EntityNotFoundException(
                                                    "Person", domain.id().toString()));
            mapper.updateEntity(entity, domain);
        } else {
            entity = mapper.toNewEntity(domain);
        }
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Person> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public boolean existsByPersonCodeAndTenantId(String personCode, String tenantId) {
        return repository.existsByPersonCodeAndTenantId(personCode, tenantId);
    }

    @Override
    public Page<Person> findAll(
            String tenantId,
            String personTypeCode,
            String coreStatus,
            String search,
            Pageable pageable) {
        Specification<PersonJpaEntity> spec =
                Specification.where(equalsTenantId(tenantId))
                        .and(equalsPersonTypeCode(personTypeCode))
                        .and(equalsCoreStatus(coreStatus))
                        .and(likePersonCode(search));
        return repository.findAll(spec, pageable).map(mapper::toDomain);
    }

    @Override
    public long countByTenantIdAndCoreStatus(String tenantId, CoreStatus coreStatus) {
        return repository.countByTenantIdAndCoreStatus(tenantId, coreStatus.name());
    }

    private Specification<PersonJpaEntity> equalsTenantId(String tenantId) {
        return (root, query, cb) -> cb.equal(root.get("tenantId"), tenantId);
    }

    private Specification<PersonJpaEntity> equalsPersonTypeCode(String value) {
        return value == null
                ? null
                : (root, query, cb) -> cb.equal(root.get("personTypeCode"), value);
    }

    private Specification<PersonJpaEntity> equalsCoreStatus(String value) {
        return value == null ? null : (root, query, cb) -> cb.equal(root.get("coreStatus"), value);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private Specification<PersonJpaEntity> likePersonCode(String search) {
        return search == null
                ? null
                : (root, query, cb) ->
                        cb.like(cb.lower(root.get("personCode")), "%" + search.toLowerCase() + "%");
    }
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitivecoverage.PersonSensitiveCoverage;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitivecoverage.PersonSensitiveCoverageRepositoryPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonSensitiveCoveragePersistenceAdapter
        implements PersonSensitiveCoverageRepositoryPort {

    private final PersonSensitiveCoverageJpaRepository repository;
    private final PersonSensitiveCoverageMapper mapper;

    @Override
    public List<PersonSensitiveCoverage> findAllByPersonIdAndTenantId(
            Long personId, String tenantId) {
        return repository.findAllByPersonIdAndTenantId(personId, tenantId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public PersonSensitiveCoverage save(PersonSensitiveCoverage coverage) {
        PersonSensitiveCoverageJpaEntity entity;
        if (coverage.id() != null) {
            entity = repository.findById(coverage.id()).orElseThrow();
        } else {
            entity = mapper.toNewEntity(coverage);
        }
        return mapper.toDomain(repository.save(entity));
    }
}

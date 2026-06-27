package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonRepositoryPort {

    Person save(Person person);

    Optional<Person> findById(Long id);

    boolean existsByPersonCodeAndTenantId(String personCode, String tenantId);

    Page<Person> findAll(
            String tenantId,
            String personTypeCode,
            String coreStatus,
            String search,
            Pageable pageable);

    long countByTenantIdAndCoreStatus(String tenantId, CoreStatus coreStatus);

    void deleteById(Long id);
}

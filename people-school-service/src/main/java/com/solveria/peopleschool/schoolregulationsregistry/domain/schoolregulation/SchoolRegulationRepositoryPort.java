package com.solveria.peopleschool.schoolregulationsregistry.domain.schoolregulation;

import java.util.List;
import java.util.Optional;

public interface SchoolRegulationRepositoryPort {
    SchoolRegulation save(SchoolRegulation regulation);

    Optional<SchoolRegulation> findById(Long id);

    List<SchoolRegulation> findAllByTenantId(String tenantId);

    void deleteById(Long id);

    boolean existsByRegulationCodeAndTenantId(String code, String tenantId);
}

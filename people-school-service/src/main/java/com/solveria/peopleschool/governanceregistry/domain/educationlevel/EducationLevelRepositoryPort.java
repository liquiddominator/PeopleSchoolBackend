package com.solveria.peopleschool.governanceregistry.domain.educationlevel;

import java.util.List;
import java.util.Optional;

public interface EducationLevelRepositoryPort {
    EducationLevel save(EducationLevel level);

    Optional<EducationLevel> findById(Long id);

    List<EducationLevel> findAllByTenantId(String tenantId);

    void deleteById(Long id);

    boolean existsByLevelCodeAndTenantId(String code, String tenantId);
}

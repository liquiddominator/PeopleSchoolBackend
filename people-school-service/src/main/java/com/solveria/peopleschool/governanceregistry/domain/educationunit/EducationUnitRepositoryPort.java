package com.solveria.peopleschool.governanceregistry.domain.educationunit;

import java.util.Optional;

public interface EducationUnitRepositoryPort {
    EducationUnit save(EducationUnit educationUnit);

    Optional<EducationUnit> findByTenantId(String tenantId);

    Optional<EducationUnit> findById(Long id);

    boolean existsByUnitCodeAndTenantId(String unitCode, String tenantId);
}

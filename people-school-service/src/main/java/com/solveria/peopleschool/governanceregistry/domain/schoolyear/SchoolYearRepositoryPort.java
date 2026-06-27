package com.solveria.peopleschool.governanceregistry.domain.schoolyear;

import java.util.List;
import java.util.Optional;

public interface SchoolYearRepositoryPort {
    SchoolYear save(SchoolYear schoolYear);

    Optional<SchoolYear> findById(Long id);

    List<SchoolYear> findAllByTenantId(String tenantId);

    void clearCurrentDefaultForTenant(String tenantId);

    void deleteById(Long id);

    boolean existsBySchoolYearCodeAndTenantId(String code, String tenantId);
}

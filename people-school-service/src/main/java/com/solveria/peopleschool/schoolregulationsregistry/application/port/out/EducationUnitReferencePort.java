package com.solveria.peopleschool.schoolregulationsregistry.application.port.out;

import java.util.Optional;

public interface EducationUnitReferencePort {

    Optional<EducationUnitReference> findByTenantId(String tenantId);

    record EducationUnitReference(Long id, boolean active) {}
}

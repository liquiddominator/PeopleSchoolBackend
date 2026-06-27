package com.solveria.peopleschool.studentregistry.domain.studentprofile;

import java.util.List;
import java.util.Optional;

public interface StudentProfileRepositoryPort {
    StudentProfile save(StudentProfile profile);

    Optional<StudentProfile> findById(Long id);

    Optional<StudentProfile> findByPersonIdAndTenantId(Long personId, String tenantId);

    List<StudentProfile> findAllByTenantId(String tenantId);

    void deleteById(Long id);

    boolean existsByPersonIdAndTenantId(Long personId, String tenantId);

    boolean existsByStudentCodeAndTenantId(String studentCode, String tenantId);
}

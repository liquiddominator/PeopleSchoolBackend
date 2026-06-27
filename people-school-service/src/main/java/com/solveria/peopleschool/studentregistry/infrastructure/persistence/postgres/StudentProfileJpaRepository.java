package com.solveria.peopleschool.studentregistry.infrastructure.persistence.postgres;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentProfileJpaRepository extends JpaRepository<StudentProfileJpaEntity, Long> {

    List<StudentProfileJpaEntity> findAllByTenantIdOrderByStudentCodeAsc(String tenantId);

    Optional<StudentProfileJpaEntity> findByPersonIdAndTenantId(Long personId, String tenantId);

    boolean existsByPersonIdAndTenantId(Long personId, String tenantId);

    boolean existsByStudentCodeAndTenantId(String studentCode, String tenantId);
}

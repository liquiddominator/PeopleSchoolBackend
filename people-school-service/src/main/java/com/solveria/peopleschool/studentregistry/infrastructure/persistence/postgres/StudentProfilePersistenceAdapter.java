package com.solveria.peopleschool.studentregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.studentregistry.domain.studentprofile.StudentProfile;
import com.solveria.peopleschool.studentregistry.domain.studentprofile.StudentProfileRepositoryPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentProfilePersistenceAdapter implements StudentProfileRepositoryPort {

    private final StudentProfileJpaRepository repository;
    private final StudentProfileMapper mapper;

    @Override
    public StudentProfile save(StudentProfile domain) {
        StudentProfileJpaEntity entity;
        if (domain.id() != null) {
            entity =
                    repository
                            .findById(domain.id())
                            .orElseThrow(
                                    () ->
                                            new EntityNotFoundException(
                                                    "StudentProfile", domain.id().toString()));
            mapper.updateEntity(entity, domain);
        } else {
            entity = mapper.toNewEntity(domain);
        }
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<StudentProfile> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<StudentProfile> findByPersonIdAndTenantId(Long personId, String tenantId) {
        return repository.findByPersonIdAndTenantId(personId, tenantId).map(mapper::toDomain);
    }

    @Override
    public List<StudentProfile> findAllByTenantId(String tenantId) {
        return repository.findAllByTenantIdOrderByStudentCodeAsc(tenantId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsByPersonIdAndTenantId(Long personId, String tenantId) {
        return repository.existsByPersonIdAndTenantId(personId, tenantId);
    }

    @Override
    public boolean existsByStudentCodeAndTenantId(String studentCode, String tenantId) {
        return repository.existsByStudentCodeAndTenantId(studentCode, tenantId);
    }
}

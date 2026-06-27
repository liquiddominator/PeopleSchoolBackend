package com.solveria.peopleschool.studentregistry.application.usecase;

import com.solveria.peopleschool.studentregistry.domain.studentprofile.StudentProfile;
import com.solveria.peopleschool.studentregistry.domain.studentprofile.StudentProfileRepositoryPort;
import java.util.List;

public class GetStudentProfilesUseCase {

    private final StudentProfileRepositoryPort repository;

    public GetStudentProfilesUseCase(StudentProfileRepositoryPort repository) {
        this.repository = repository;
    }

    public List<StudentProfile> execute(String tenantId) {
        return repository.findAllByTenantId(tenantId);
    }
}

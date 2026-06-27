package com.solveria.peopleschool.studentregistry.application.usecase;

import com.solveria.core.shared.exceptions.BusinessRuleViolationException;
import com.solveria.peopleschool.studentregistry.application.dto.CreateStudentProfileRequest;
import com.solveria.peopleschool.studentregistry.domain.studentprofile.StudentProfile;
import com.solveria.peopleschool.studentregistry.domain.studentprofile.StudentProfileRepositoryPort;

public class CreateStudentProfileUseCase {

    private final StudentProfileRepositoryPort repository;

    public CreateStudentProfileUseCase(StudentProfileRepositoryPort repository) {
        this.repository = repository;
    }

    public StudentProfile execute(CreateStudentProfileRequest request, String tenantId) {
        if (repository.existsByPersonIdAndTenantId(request.personId(), tenantId)) {
            throw new BusinessRuleViolationException("student.profile.person.already.has.profile");
        }
        if (repository.existsByStudentCodeAndTenantId(request.studentCode(), tenantId)) {
            throw new BusinessRuleViolationException("student.profile.code.already.exists");
        }
        StudentProfile profile =
                new StudentProfile(
                        null,
                        request.personId(),
                        request.studentCode(),
                        request.studentStatus(),
                        request.schoolEntryDate(),
                        request.firstSchoolYearId(),
                        request.studentStatus().name(),
                        tenantId,
                        null,
                        null,
                        null,
                        null,
                        null);
        return repository.save(profile);
    }
}

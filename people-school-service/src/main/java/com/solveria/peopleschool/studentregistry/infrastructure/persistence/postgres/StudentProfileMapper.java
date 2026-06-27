package com.solveria.peopleschool.studentregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.studentregistry.domain.studentprofile.StudentProfile;
import com.solveria.peopleschool.studentregistry.domain.studentprofile.StudentStatus;
import org.springframework.stereotype.Component;

@Component
public class StudentProfileMapper {

    public StudentProfile toDomain(StudentProfileJpaEntity e) {
        return new StudentProfile(
                e.getId(),
                e.getPersonId(),
                e.getStudentCode(),
                StudentStatus.valueOf(e.getStudentStatus()),
                e.getSchoolEntryDate(),
                e.getFirstSchoolYearId(),
                e.getCurrentOperationalStatus(),
                e.getTenantId(),
                e.getCreatedAt(),
                e.getCreatedBy(),
                e.getLastModifiedAt(),
                e.getLastModifiedBy(),
                e.getVersion());
    }

    public StudentProfileJpaEntity toNewEntity(StudentProfile d) {
        StudentProfileJpaEntity entity = new StudentProfileJpaEntity();
        entity.setTenantId(d.tenantId());
        entity.setPersonId(d.personId());
        entity.setStudentCode(d.studentCode());
        entity.setStudentStatus(d.studentStatus().name());
        entity.setSchoolEntryDate(d.schoolEntryDate());
        entity.setFirstSchoolYearId(d.firstSchoolYearId());
        entity.setCurrentOperationalStatus(d.currentOperationalStatus());
        return entity;
    }

    public void updateEntity(StudentProfileJpaEntity entity, StudentProfile d) {
        entity.setStudentStatus(d.studentStatus().name());
        entity.setSchoolEntryDate(d.schoolEntryDate());
        entity.setFirstSchoolYearId(d.firstSchoolYearId());
        entity.setCurrentOperationalStatus(d.currentOperationalStatus());
    }
}

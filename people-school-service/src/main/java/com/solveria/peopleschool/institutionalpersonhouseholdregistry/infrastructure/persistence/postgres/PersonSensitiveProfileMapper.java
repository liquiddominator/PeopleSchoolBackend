package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitiveprofile.PersonSensitiveProfile;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitiveprofile.SensitiveProfileStatus;
import org.springframework.stereotype.Component;

@Component
public class PersonSensitiveProfileMapper {

    public PersonSensitiveProfile toDomain(PersonSensitiveProfileJpaEntity entity) {
        return new PersonSensitiveProfile(
                entity.getId(),
                entity.getPersonId(),
                entity.getBloodTypeCode(),
                entity.getEmergencyMedicalNotes(),
                SensitiveProfileStatus.valueOf(entity.getSensitiveProfileStatus()),
                entity.getLastReviewedAt(),
                entity.getLastReviewedBy(),
                entity.getTenantId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getLastModifiedAt(),
                entity.getLastModifiedBy(),
                entity.getVersion());
    }

    public PersonSensitiveProfileJpaEntity toNewEntity(PersonSensitiveProfile domain) {
        PersonSensitiveProfileJpaEntity entity = new PersonSensitiveProfileJpaEntity();
        entity.setTenantId(domain.tenantId());
        entity.setPersonId(domain.personId());
        entity.setBloodTypeCode(domain.bloodTypeCode());
        entity.setEmergencyMedicalNotes(domain.emergencyMedicalNotes());
        entity.setSensitiveProfileStatus(domain.sensitiveProfileStatus().name());
        entity.setLastReviewedAt(domain.lastReviewedAt());
        entity.setLastReviewedBy(domain.lastReviewedBy());
        return entity;
    }

    public void updateEntity(
            PersonSensitiveProfileJpaEntity entity, PersonSensitiveProfile domain) {
        entity.setBloodTypeCode(domain.bloodTypeCode());
        entity.setEmergencyMedicalNotes(domain.emergencyMedicalNotes());
        entity.setSensitiveProfileStatus(domain.sensitiveProfileStatus().name());
        entity.setLastReviewedAt(domain.lastReviewedAt());
        entity.setLastReviewedBy(domain.lastReviewedBy());
    }
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.emergencycontact.EmergencyContact;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.emergencycontact.EmergencyStatus;
import org.springframework.stereotype.Component;

@Component
public class EmergencyContactMapper {

    public EmergencyContact toDomain(EmergencyContactJpaEntity entity) {
        return new EmergencyContact(
                entity.getId(),
                entity.getPersonId(),
                entity.getContactPersonId(),
                entity.getEmergencyPriority(),
                entity.getRelationshipLabel(),
                EmergencyStatus.valueOf(entity.getEmergencyStatus()),
                entity.getTenantId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getLastModifiedAt(),
                entity.getLastModifiedBy(),
                entity.getVersion());
    }

    public EmergencyContactJpaEntity toNewEntity(EmergencyContact domain) {
        EmergencyContactJpaEntity entity = new EmergencyContactJpaEntity();
        entity.setTenantId(domain.tenantId());
        entity.setPersonId(domain.personId());
        entity.setContactPersonId(domain.contactPersonId());
        entity.setEmergencyPriority(domain.emergencyPriority());
        entity.setRelationshipLabel(domain.relationshipLabel());
        entity.setEmergencyStatus(domain.emergencyStatus().name());
        return entity;
    }
}

package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnit;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.UnitStatus;
import org.springframework.stereotype.Component;

@Component
public class EducationUnitMapper {

    public EducationUnit toDomain(EducationUnitJpaEntity e) {
        return new EducationUnit(
                e.getId(),
                e.getUnitCode(),
                e.getOfficialName(),
                e.getShortName(),
                e.getMinistryRueCode(),
                e.getAdministrativeDependency(),
                e.getInstitutionalType(),
                e.getServiceType(),
                e.getPhoneNumber(),
                e.getEmailAddress(),
                e.getAddressLine(),
                UnitStatus.valueOf(e.getUnitStatus()),
                e.getTenantId(),
                e.getCreatedAt(),
                e.getCreatedBy(),
                e.getLastModifiedAt(),
                e.getLastModifiedBy(),
                e.getVersion());
    }

    public EducationUnitJpaEntity toNewEntity(EducationUnit d) {
        EducationUnitJpaEntity entity = new EducationUnitJpaEntity();
        entity.setTenantId(d.tenantId());
        entity.setUnitCode(d.unitCode());
        entity.setOfficialName(d.officialName());
        entity.setShortName(d.shortName());
        entity.setMinistryRueCode(d.ministryRueCode());
        entity.setAdministrativeDependency(
                d.administrativeDependency() != null ? d.administrativeDependency() : "PRIVADO");
        entity.setInstitutionalType(
                d.institutionalType() != null ? d.institutionalType() : "PRIVADO");
        entity.setServiceType(d.serviceType() != null ? d.serviceType() : "PRESENCIAL");
        entity.setPhoneNumber(d.phoneNumber());
        entity.setEmailAddress(d.emailAddress());
        entity.setAddressLine(d.addressLine());
        entity.setUnitStatus(d.unitStatus().name());
        return entity;
    }

    public void updateEntity(EducationUnitJpaEntity entity, EducationUnit d) {
        entity.setUnitCode(d.unitCode());
        entity.setOfficialName(d.officialName());
        entity.setShortName(d.shortName());
        entity.setMinistryRueCode(d.ministryRueCode());
        entity.setAdministrativeDependency(d.administrativeDependency());
        entity.setInstitutionalType(d.institutionalType());
        entity.setServiceType(d.serviceType());
        entity.setPhoneNumber(d.phoneNumber());
        entity.setEmailAddress(d.emailAddress());
        entity.setAddressLine(d.addressLine());
        entity.setUnitStatus(d.unitStatus().name());
    }
}

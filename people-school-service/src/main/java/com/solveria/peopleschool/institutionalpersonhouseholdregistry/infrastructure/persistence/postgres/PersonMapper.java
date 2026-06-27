package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.CoreStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.Person;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.PersonTypeCode;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public Person toDomain(PersonJpaEntity entity) {
        return new Person(
                entity.getId(),
                entity.getPersonCode(),
                PersonTypeCode.valueOf(entity.getPersonTypeCode()),
                CoreStatus.valueOf(entity.getCoreStatus()),
                entity.getPrimaryPhotoAssetId(),
                entity.getNombres(),
                entity.getApellidos(),
                entity.getTenantId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getLastModifiedAt(),
                entity.getLastModifiedBy(),
                entity.getVersion());
    }

    public PersonJpaEntity toNewEntity(Person domain) {
        PersonJpaEntity entity = new PersonJpaEntity();
        entity.setTenantId(domain.tenantId());
        entity.setPersonCode(domain.personCode());
        entity.setPersonTypeCode(domain.personTypeCode().name());
        entity.setCoreStatus(domain.coreStatus().name());
        entity.setPrimaryPhotoAssetId(domain.primaryPhotoAssetId());
        entity.setNombres(domain.nombres());
        entity.setApellidos(domain.apellidos());
        return entity;
    }

    public void updateEntity(PersonJpaEntity entity, Person domain) {
        entity.setPersonTypeCode(domain.personTypeCode().name());
        entity.setCoreStatus(domain.coreStatus().name());
        entity.setPrimaryPhotoAssetId(domain.primaryPhotoAssetId());
        entity.setNombres(domain.nombres());
        entity.setApellidos(domain.apellidos());
    }
}

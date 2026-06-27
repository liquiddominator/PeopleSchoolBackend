package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.address.PersonAddress;
import org.springframework.stereotype.Component;

@Component
public class PersonAddressMapper {

    public PersonAddress toDomain(PersonAddressJpaEntity entity) {
        return new PersonAddress(
                entity.getId(),
                entity.getPersonId(),
                entity.getPais(),
                entity.getCiudad(),
                entity.getLinea1(),
                entity.getLinea2(),
                entity.getEsPrincipal(),
                entity.getTenantId(),
                entity.getVersion());
    }

    public PersonAddressJpaEntity toNewEntity(PersonAddress domain) {
        PersonAddressJpaEntity entity = new PersonAddressJpaEntity();
        entity.setTenantId(domain.tenantId());
        entity.setPersonId(domain.personId());
        entity.setPais(domain.pais());
        entity.setCiudad(domain.ciudad());
        entity.setLinea1(domain.linea1());
        entity.setLinea2(domain.linea2());
        entity.setEsPrincipal(domain.esPrincipal());
        return entity;
    }

    public void updateEntity(PersonAddressJpaEntity entity, PersonAddress domain) {
        entity.setPais(domain.pais());
        entity.setCiudad(domain.ciudad());
        entity.setLinea1(domain.linea1());
        entity.setLinea2(domain.linea2());
        entity.setEsPrincipal(domain.esPrincipal());
    }
}

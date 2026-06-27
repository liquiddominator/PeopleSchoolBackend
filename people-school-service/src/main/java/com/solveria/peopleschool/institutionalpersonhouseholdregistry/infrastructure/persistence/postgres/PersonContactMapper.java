package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.contact.ContactType;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.contact.ContactUse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.contact.PersonContact;
import org.springframework.stereotype.Component;

@Component
public class PersonContactMapper {

    public PersonContact toDomain(PersonContactJpaEntity entity) {
        return new PersonContact(
                entity.getId(),
                entity.getPersonId(),
                ContactType.valueOf(entity.getTipo()),
                entity.getValor(),
                ContactUse.valueOf(entity.getUso()),
                entity.getEsPrincipal(),
                entity.getTenantId(),
                entity.getVersion());
    }

    public PersonContactJpaEntity toNewEntity(PersonContact domain) {
        PersonContactJpaEntity entity = new PersonContactJpaEntity();
        entity.setTenantId(domain.tenantId());
        entity.setPersonId(domain.personId());
        entity.setTipo(domain.tipo().name());
        entity.setValor(domain.valor());
        entity.setUso(domain.uso().name());
        entity.setEsPrincipal(domain.esPrincipal());
        return entity;
    }

    public void updateEntity(PersonContactJpaEntity entity, PersonContact domain) {
        entity.setTipo(domain.tipo().name());
        entity.setValor(domain.valor());
        entity.setUso(domain.uso().name());
        entity.setEsPrincipal(domain.esPrincipal());
    }
}

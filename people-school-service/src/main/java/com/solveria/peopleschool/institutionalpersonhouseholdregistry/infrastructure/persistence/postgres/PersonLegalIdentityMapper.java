package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.legalidentity.DocumentType;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.legalidentity.IdentityStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.legalidentity.PersonLegalIdentity;
import org.springframework.stereotype.Component;

@Component
public class PersonLegalIdentityMapper {

    public PersonLegalIdentity toDomain(PersonLegalIdentityJpaEntity entity) {
        return new PersonLegalIdentity(
                entity.getId(),
                entity.getPersonId(),
                DocumentType.valueOf(entity.getTipo()),
                entity.getNumero(),
                entity.getPaisEmisor(),
                entity.getEsPrincipal(),
                IdentityStatus.valueOf(entity.getEstado()),
                entity.getTenantId(),
                entity.getVersion());
    }

    public PersonLegalIdentityJpaEntity toNewEntity(PersonLegalIdentity domain) {
        PersonLegalIdentityJpaEntity entity = new PersonLegalIdentityJpaEntity();
        entity.setTenantId(domain.tenantId());
        entity.setPersonId(domain.personId());
        entity.setTipo(domain.tipo().name());
        entity.setNumero(domain.numero());
        entity.setPaisEmisor(domain.paisEmisor());
        entity.setEsPrincipal(domain.esPrincipal());
        entity.setEstado(domain.estado().name());
        return entity;
    }

    public void updateEntity(PersonLegalIdentityJpaEntity entity, PersonLegalIdentity domain) {
        entity.setTipo(domain.tipo().name());
        entity.setNumero(domain.numero());
        entity.setPaisEmisor(domain.paisEmisor());
        entity.setEsPrincipal(domain.esPrincipal());
        entity.setEstado(domain.estado().name());
    }
}

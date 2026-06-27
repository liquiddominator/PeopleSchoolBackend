package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonLegalIdentityJpaRepository
        extends JpaRepository<PersonLegalIdentityJpaEntity, Long> {

    List<PersonLegalIdentityJpaEntity> findByPersonIdAndTenantId(Long personId, String tenantId);

    boolean existsByTipoAndNumeroAndTenantId(String tipo, String numero, String tenantId);

    boolean existsByTipoAndNumeroAndTenantIdAndIdNot(
            String tipo, String numero, String tenantId, Long id);
}

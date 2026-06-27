package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonAddressJpaRepository extends JpaRepository<PersonAddressJpaEntity, Long> {

    List<PersonAddressJpaEntity> findByPersonIdAndTenantId(Long personId, String tenantId);
}

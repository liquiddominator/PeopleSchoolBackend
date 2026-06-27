package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.emergencycontact;

import java.util.List;
import java.util.Optional;

public interface EmergencyContactRepositoryPort {
    EmergencyContact save(EmergencyContact contact);

    Optional<EmergencyContact> findById(Long id);

    List<EmergencyContact> findAllByPersonIdAndTenantId(Long personId, String tenantId);

    void deleteById(Long id);
}

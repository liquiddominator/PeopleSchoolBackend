package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.emergencycontact.EmergencyContact;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.emergencycontact.EmergencyContactRepositoryPort;
import java.util.List;

public class GetEmergencyContactsUseCase {

    private final EmergencyContactRepositoryPort repository;

    public GetEmergencyContactsUseCase(EmergencyContactRepositoryPort repository) {
        this.repository = repository;
    }

    public List<EmergencyContact> execute(Long personId, String tenantId) {
        return repository.findAllByPersonIdAndTenantId(personId, tenantId);
    }
}

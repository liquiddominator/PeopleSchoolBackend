package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreateEmergencyContactRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.emergencycontact.EmergencyContact;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.emergencycontact.EmergencyContactRepositoryPort;

public class RegisterEmergencyContactUseCase {

    private final EmergencyContactRepositoryPort repository;

    public RegisterEmergencyContactUseCase(EmergencyContactRepositoryPort repository) {
        this.repository = repository;
    }

    public EmergencyContact execute(CreateEmergencyContactRequest request, String tenantId) {
        EmergencyContact contact =
                new EmergencyContact(
                        null,
                        request.personId(),
                        request.contactPersonId(),
                        request.emergencyPriority(),
                        request.relationshipLabel(),
                        request.emergencyStatus(),
                        tenantId,
                        null,
                        null,
                        null,
                        null,
                        null);
        return repository.save(contact);
    }
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.address.PersonAddressRepositoryPort;

public class DeleteAddressUseCase {

    private final PersonAddressRepositoryPort addressRepository;

    public DeleteAddressUseCase(PersonAddressRepositoryPort addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void execute(Long id, String tenantId) {
        addressRepository
                .findById(id)
                .filter(a -> a.tenantId().equals(tenantId))
                .orElseThrow(() -> new EntityNotFoundException("PersonAddress", id.toString()));
        addressRepository.deleteById(id);
    }
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.AddressRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.address.PersonAddress;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.address.PersonAddressRepositoryPort;

public class UpdateAddressUseCase {

    private final PersonAddressRepositoryPort addressRepository;

    public UpdateAddressUseCase(PersonAddressRepositoryPort addressRepository) {
        this.addressRepository = addressRepository;
    }

    public PersonAddress execute(Long id, AddressRequest request, String tenantId) {
        PersonAddress existing =
                addressRepository
                        .findById(id)
                        .filter(a -> a.tenantId().equals(tenantId))
                        .orElseThrow(
                                () -> new EntityNotFoundException("PersonAddress", id.toString()));

        PersonAddress updated =
                new PersonAddress(
                        existing.id(),
                        existing.personId(),
                        request.pais(),
                        request.ciudad(),
                        request.linea1(),
                        request.linea2(),
                        request.esPrincipal(),
                        existing.tenantId(),
                        existing.version());
        return addressRepository.save(updated);
    }
}

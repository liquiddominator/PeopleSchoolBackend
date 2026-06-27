package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.AddressRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.address.PersonAddress;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.address.PersonAddressRepositoryPort;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.PersonRepositoryPort;

public class AddAddressUseCase {

    private final PersonAddressRepositoryPort addressRepository;
    private final PersonRepositoryPort personRepository;

    public AddAddressUseCase(
            PersonAddressRepositoryPort addressRepository, PersonRepositoryPort personRepository) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
    }

    public PersonAddress execute(Long personId, AddressRequest request, String tenantId) {
        personRepository
                .findById(personId)
                .filter(p -> p.tenantId().equals(tenantId))
                .orElseThrow(() -> new EntityNotFoundException("Person", personId.toString()));

        PersonAddress address =
                new PersonAddress(
                        null,
                        personId,
                        request.pais(),
                        request.ciudad(),
                        request.linea1(),
                        request.linea2(),
                        request.esPrincipal(),
                        tenantId,
                        null);
        return addressRepository.save(address);
    }
}

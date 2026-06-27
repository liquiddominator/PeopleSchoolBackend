package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.address.PersonAddress;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.address.PersonAddressRepositoryPort;
import java.util.List;

public class ListAddressesUseCase {

    private final PersonAddressRepositoryPort addressRepository;

    public ListAddressesUseCase(PersonAddressRepositoryPort addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<PersonAddress> execute(Long personId, String tenantId) {
        return addressRepository.findByPersonIdAndTenantId(personId, tenantId);
    }
}

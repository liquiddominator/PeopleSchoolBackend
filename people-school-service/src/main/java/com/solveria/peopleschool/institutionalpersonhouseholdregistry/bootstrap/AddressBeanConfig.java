package com.solveria.peopleschool.institutionalpersonhouseholdregistry.bootstrap;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.AddAddressUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.DeleteAddressUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.ListAddressesUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.UpdateAddressUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.address.PersonAddressRepositoryPort;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.PersonRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressBeanConfig {

    @Bean
    public AddAddressUseCase addAddressUseCase(
            PersonAddressRepositoryPort addressRepository, PersonRepositoryPort personRepository) {
        return new AddAddressUseCase(addressRepository, personRepository);
    }

    @Bean
    public ListAddressesUseCase listAddressesUseCase(
            PersonAddressRepositoryPort addressRepository) {
        return new ListAddressesUseCase(addressRepository);
    }

    @Bean
    public UpdateAddressUseCase updateAddressUseCase(
            PersonAddressRepositoryPort addressRepository) {
        return new UpdateAddressUseCase(addressRepository);
    }

    @Bean
    public DeleteAddressUseCase deleteAddressUseCase(
            PersonAddressRepositoryPort addressRepository) {
        return new DeleteAddressUseCase(addressRepository);
    }
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.bootstrap;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.AddContactUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.DeleteContactUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.ListContactsUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.UpdateContactUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.contact.PersonContactRepositoryPort;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.PersonRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContactBeanConfig {

    @Bean
    public AddContactUseCase addContactUseCase(
            PersonContactRepositoryPort contactRepository, PersonRepositoryPort personRepository) {
        return new AddContactUseCase(contactRepository, personRepository);
    }

    @Bean
    public ListContactsUseCase listContactsUseCase(PersonContactRepositoryPort contactRepository) {
        return new ListContactsUseCase(contactRepository);
    }

    @Bean
    public UpdateContactUseCase updateContactUseCase(
            PersonContactRepositoryPort contactRepository) {
        return new UpdateContactUseCase(contactRepository);
    }

    @Bean
    public DeleteContactUseCase deleteContactUseCase(
            PersonContactRepositoryPort contactRepository) {
        return new DeleteContactUseCase(contactRepository);
    }
}

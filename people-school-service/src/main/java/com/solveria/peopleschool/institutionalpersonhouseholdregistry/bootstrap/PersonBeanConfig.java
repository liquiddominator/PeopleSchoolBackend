package com.solveria.peopleschool.institutionalpersonhouseholdregistry.bootstrap;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.CreatePersonUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.DeletePersonUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetPersonByIdUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetPersonsUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.UpdatePersonUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.PersonRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonBeanConfig {

    @Bean
    public CreatePersonUseCase createPersonUseCase(PersonRepositoryPort personRepository) {
        return new CreatePersonUseCase(personRepository);
    }

    @Bean
    public GetPersonByIdUseCase getPersonByIdUseCase(PersonRepositoryPort personRepository) {
        return new GetPersonByIdUseCase(personRepository);
    }

    @Bean
    public GetPersonsUseCase getPersonsUseCase(PersonRepositoryPort personRepository) {
        return new GetPersonsUseCase(personRepository);
    }

    @Bean
    public UpdatePersonUseCase updatePersonUseCase(PersonRepositoryPort personRepository) {
        return new UpdatePersonUseCase(personRepository);
    }

    @Bean
    public DeletePersonUseCase deletePersonUseCase(PersonRepositoryPort personRepository) {
        return new DeletePersonUseCase(personRepository);
    }
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.bootstrap;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.CreateSensitiveProfileUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetSensitiveProfileUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.UpdateSensitiveProfileUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitiveprofile.PersonSensitiveProfileRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonSensitiveProfileBeanConfig {

    @Bean
    public CreateSensitiveProfileUseCase createSensitiveProfileUseCase(
            PersonSensitiveProfileRepositoryPort repository) {
        return new CreateSensitiveProfileUseCase(repository);
    }

    @Bean
    public UpdateSensitiveProfileUseCase updateSensitiveProfileUseCase(
            PersonSensitiveProfileRepositoryPort repository) {
        return new UpdateSensitiveProfileUseCase(repository);
    }

    @Bean
    public GetSensitiveProfileUseCase getSensitiveProfileUseCase(
            PersonSensitiveProfileRepositoryPort repository) {
        return new GetSensitiveProfileUseCase(repository);
    }
}

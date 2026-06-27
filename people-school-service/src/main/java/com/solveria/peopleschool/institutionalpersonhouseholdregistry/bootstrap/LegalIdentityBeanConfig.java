package com.solveria.peopleschool.institutionalpersonhouseholdregistry.bootstrap;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.AddLegalIdentityUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.DeleteLegalIdentityUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.ListLegalIdentitiesUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.UpdateLegalIdentityUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.legalidentity.PersonLegalIdentityRepositoryPort;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.PersonRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LegalIdentityBeanConfig {

    @Bean
    public AddLegalIdentityUseCase addLegalIdentityUseCase(
            PersonLegalIdentityRepositoryPort identityRepository,
            PersonRepositoryPort personRepository) {
        return new AddLegalIdentityUseCase(identityRepository, personRepository);
    }

    @Bean
    public ListLegalIdentitiesUseCase listLegalIdentitiesUseCase(
            PersonLegalIdentityRepositoryPort identityRepository) {
        return new ListLegalIdentitiesUseCase(identityRepository);
    }

    @Bean
    public UpdateLegalIdentityUseCase updateLegalIdentityUseCase(
            PersonLegalIdentityRepositoryPort identityRepository) {
        return new UpdateLegalIdentityUseCase(identityRepository);
    }

    @Bean
    public DeleteLegalIdentityUseCase deleteLegalIdentityUseCase(
            PersonLegalIdentityRepositoryPort identityRepository) {
        return new DeleteLegalIdentityUseCase(identityRepository);
    }
}

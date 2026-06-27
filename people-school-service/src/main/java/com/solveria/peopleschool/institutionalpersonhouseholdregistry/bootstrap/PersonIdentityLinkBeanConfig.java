package com.solveria.peopleschool.institutionalpersonhouseholdregistry.bootstrap;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetPersonIdentityLinksUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.LinkPersonIdentityUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personidentitylink.PersonIdentityLinkRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonIdentityLinkBeanConfig {

    @Bean
    public LinkPersonIdentityUseCase linkPersonIdentityUseCase(
            PersonIdentityLinkRepositoryPort repository) {
        return new LinkPersonIdentityUseCase(repository);
    }

    @Bean
    public GetPersonIdentityLinksUseCase getPersonIdentityLinksUseCase(
            PersonIdentityLinkRepositoryPort repository) {
        return new GetPersonIdentityLinksUseCase(repository);
    }
}

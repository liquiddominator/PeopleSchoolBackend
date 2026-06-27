package com.solveria.peopleschool.institutionalpersonhouseholdregistry.bootstrap;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetAffiliationsUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.RegisterAffiliationUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalaffiliation.InstitutionalAffiliationRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InstitutionalAffiliationBeanConfig {

    @Bean
    public RegisterAffiliationUseCase registerAffiliationUseCase(
            InstitutionalAffiliationRepositoryPort repository) {
        return new RegisterAffiliationUseCase(repository);
    }

    @Bean
    public GetAffiliationsUseCase getAffiliationsUseCase(
            InstitutionalAffiliationRepositoryPort repository) {
        return new GetAffiliationsUseCase(repository);
    }
}

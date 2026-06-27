package com.solveria.peopleschool.institutionalpersonhouseholdregistry.bootstrap;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetDocumentReferencesUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.RegisterDocumentReferenceUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.persondocumentreference.PersonDocumentReferenceRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonDocumentReferenceBeanConfig {

    @Bean
    public RegisterDocumentReferenceUseCase registerDocumentReferenceUseCase(
            PersonDocumentReferenceRepositoryPort repository) {
        return new RegisterDocumentReferenceUseCase(repository);
    }

    @Bean
    public GetDocumentReferencesUseCase getDocumentReferencesUseCase(
            PersonDocumentReferenceRepositoryPort repository) {
        return new GetDocumentReferencesUseCase(repository);
    }
}

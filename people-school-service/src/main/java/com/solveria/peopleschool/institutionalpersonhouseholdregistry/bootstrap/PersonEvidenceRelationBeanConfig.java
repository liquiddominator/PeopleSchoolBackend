package com.solveria.peopleschool.institutionalpersonhouseholdregistry.bootstrap;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetEvidenceRelationsUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.RegisterEvidenceRelationUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personevidencerelation.PersonEvidenceRelationRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonEvidenceRelationBeanConfig {

    @Bean
    public RegisterEvidenceRelationUseCase registerEvidenceRelationUseCase(
            PersonEvidenceRelationRepositoryPort repository) {
        return new RegisterEvidenceRelationUseCase(repository);
    }

    @Bean
    public GetEvidenceRelationsUseCase getEvidenceRelationsUseCase(
            PersonEvidenceRelationRepositoryPort repository) {
        return new GetEvidenceRelationsUseCase(repository);
    }
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.bootstrap;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetGuardianRelationshipsUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.RegisterGuardianRelationshipUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.guardianrelationship.GuardianRelationshipRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GuardianRelationshipBeanConfig {

    @Bean
    public RegisterGuardianRelationshipUseCase registerGuardianRelationshipUseCase(
            GuardianRelationshipRepositoryPort repository) {
        return new RegisterGuardianRelationshipUseCase(repository);
    }

    @Bean
    public GetGuardianRelationshipsUseCase getGuardianRelationshipsUseCase(
            GuardianRelationshipRepositoryPort repository) {
        return new GetGuardianRelationshipsUseCase(repository);
    }
}

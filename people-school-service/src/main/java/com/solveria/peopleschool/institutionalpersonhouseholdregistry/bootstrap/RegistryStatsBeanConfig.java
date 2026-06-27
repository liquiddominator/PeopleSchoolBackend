package com.solveria.peopleschool.institutionalpersonhouseholdregistry.bootstrap;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetRegistryStatsUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.conflict.PersonConflictRepositoryPort;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.guardianrelationship.GuardianRelationshipRepositoryPort;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.HouseholdRepositoryPort;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.PersonRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegistryStatsBeanConfig {

    @Bean
    public GetRegistryStatsUseCase getRegistryStatsUseCase(
            PersonRepositoryPort personRepository,
            HouseholdRepositoryPort householdRepository,
            GuardianRelationshipRepositoryPort guardianRelationshipRepository,
            PersonConflictRepositoryPort conflictRepository) {
        return new GetRegistryStatsUseCase(
                personRepository,
                householdRepository,
                guardianRelationshipRepository,
                conflictRepository);
    }
}

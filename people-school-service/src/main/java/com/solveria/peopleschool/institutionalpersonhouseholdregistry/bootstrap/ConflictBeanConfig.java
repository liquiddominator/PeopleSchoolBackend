package com.solveria.peopleschool.institutionalpersonhouseholdregistry.bootstrap;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetConflictsUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.UpdateConflictStatusUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.conflict.PersonConflictRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConflictBeanConfig {

    @Bean
    public GetConflictsUseCase getConflictsUseCase(
            PersonConflictRepositoryPort conflictRepository) {
        return new GetConflictsUseCase(conflictRepository);
    }

    @Bean
    public UpdateConflictStatusUseCase updateConflictStatusUseCase(
            PersonConflictRepositoryPort conflictRepository) {
        return new UpdateConflictStatusUseCase(conflictRepository);
    }
}

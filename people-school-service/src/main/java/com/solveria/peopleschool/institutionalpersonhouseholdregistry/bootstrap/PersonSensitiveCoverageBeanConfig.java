package com.solveria.peopleschool.institutionalpersonhouseholdregistry.bootstrap;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetSensitiveCoveragesUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.RegisterSensitiveCoverageUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personsensitivecoverage.PersonSensitiveCoverageRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonSensitiveCoverageBeanConfig {

    @Bean
    public RegisterSensitiveCoverageUseCase registerSensitiveCoverageUseCase(
            PersonSensitiveCoverageRepositoryPort repository) {
        return new RegisterSensitiveCoverageUseCase(repository);
    }

    @Bean
    public GetSensitiveCoveragesUseCase getSensitiveCoveragesUseCase(
            PersonSensitiveCoverageRepositoryPort repository) {
        return new GetSensitiveCoveragesUseCase(repository);
    }
}

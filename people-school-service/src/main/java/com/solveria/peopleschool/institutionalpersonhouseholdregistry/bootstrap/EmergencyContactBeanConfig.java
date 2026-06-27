package com.solveria.peopleschool.institutionalpersonhouseholdregistry.bootstrap;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetEmergencyContactsUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.RegisterEmergencyContactUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.emergencycontact.EmergencyContactRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmergencyContactBeanConfig {

    @Bean
    public RegisterEmergencyContactUseCase registerEmergencyContactUseCase(
            EmergencyContactRepositoryPort repository) {
        return new RegisterEmergencyContactUseCase(repository);
    }

    @Bean
    public GetEmergencyContactsUseCase getEmergencyContactsUseCase(
            EmergencyContactRepositoryPort repository) {
        return new GetEmergencyContactsUseCase(repository);
    }
}

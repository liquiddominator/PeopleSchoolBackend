package com.solveria.peopleschool.institutionalpersonhouseholdregistry.bootstrap;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetPickupAuthorizationsUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.RegisterPickupAuthorizationUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.pickupauthorization.PickupAuthorizationRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PickupAuthorizationBeanConfig {

    @Bean
    public RegisterPickupAuthorizationUseCase registerPickupAuthorizationUseCase(
            PickupAuthorizationRepositoryPort repository) {
        return new RegisterPickupAuthorizationUseCase(repository);
    }

    @Bean
    public GetPickupAuthorizationsUseCase getPickupAuthorizationsUseCase(
            PickupAuthorizationRepositoryPort repository) {
        return new GetPickupAuthorizationsUseCase(repository);
    }
}

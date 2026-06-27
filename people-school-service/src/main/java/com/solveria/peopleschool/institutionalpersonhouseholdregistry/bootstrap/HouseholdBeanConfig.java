package com.solveria.peopleschool.institutionalpersonhouseholdregistry.bootstrap;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.CreateHouseholdUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.DeleteHouseholdUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetHouseholdByIdUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetHouseholdsUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.UpdateHouseholdUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.HouseholdRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HouseholdBeanConfig {

    @Bean
    public CreateHouseholdUseCase createHouseholdUseCase(
            HouseholdRepositoryPort householdRepository) {
        return new CreateHouseholdUseCase(householdRepository);
    }

    @Bean
    public GetHouseholdByIdUseCase getHouseholdByIdUseCase(
            HouseholdRepositoryPort householdRepository) {
        return new GetHouseholdByIdUseCase(householdRepository);
    }

    @Bean
    public GetHouseholdsUseCase getHouseholdsUseCase(HouseholdRepositoryPort householdRepository) {
        return new GetHouseholdsUseCase(householdRepository);
    }

    @Bean
    public UpdateHouseholdUseCase updateHouseholdUseCase(
            HouseholdRepositoryPort householdRepository) {
        return new UpdateHouseholdUseCase(householdRepository);
    }

    @Bean
    public DeleteHouseholdUseCase deleteHouseholdUseCase(
            HouseholdRepositoryPort householdRepository) {
        return new DeleteHouseholdUseCase(householdRepository);
    }
}

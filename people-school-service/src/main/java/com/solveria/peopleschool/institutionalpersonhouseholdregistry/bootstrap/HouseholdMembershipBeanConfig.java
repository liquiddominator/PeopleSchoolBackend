package com.solveria.peopleschool.institutionalpersonhouseholdregistry.bootstrap;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.AddMemberToHouseholdUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetHouseholdMembersUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.RemoveMemberFromHouseholdUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.UpdateMembershipUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.HouseholdRepositoryPort;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.householdmembership.HouseholdMembershipRepositoryPort;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.PersonRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HouseholdMembershipBeanConfig {

    @Bean
    public AddMemberToHouseholdUseCase addMemberToHouseholdUseCase(
            HouseholdMembershipRepositoryPort membershipRepository,
            HouseholdRepositoryPort householdRepository,
            PersonRepositoryPort personRepository) {
        return new AddMemberToHouseholdUseCase(
                membershipRepository, householdRepository, personRepository);
    }

    @Bean
    public GetHouseholdMembersUseCase getHouseholdMembersUseCase(
            HouseholdMembershipRepositoryPort membershipRepository) {
        return new GetHouseholdMembersUseCase(membershipRepository);
    }

    @Bean
    public UpdateMembershipUseCase updateMembershipUseCase(
            HouseholdMembershipRepositoryPort membershipRepository) {
        return new UpdateMembershipUseCase(membershipRepository);
    }

    @Bean
    public RemoveMemberFromHouseholdUseCase removeMemberFromHouseholdUseCase(
            HouseholdMembershipRepositoryPort membershipRepository) {
        return new RemoveMemberFromHouseholdUseCase(membershipRepository);
    }
}

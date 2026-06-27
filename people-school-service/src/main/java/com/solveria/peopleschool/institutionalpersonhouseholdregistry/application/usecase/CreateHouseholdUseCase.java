package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.core.shared.exceptions.BusinessRuleViolationException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreateHouseholdRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.Household;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.HouseholdRepositoryPort;

public class CreateHouseholdUseCase {

    private final HouseholdRepositoryPort householdRepository;

    public CreateHouseholdUseCase(HouseholdRepositoryPort householdRepository) {
        this.householdRepository = householdRepository;
    }

    public Household execute(CreateHouseholdRequest request, String tenantId) {
        if (householdRepository.existsByHouseholdCodeAndTenantId(
                request.householdCode(), tenantId)) {
            throw new BusinessRuleViolationException("household.code.already.exists");
        }
        Household household =
                new Household(
                        null,
                        request.householdCode(),
                        request.householdName(),
                        request.householdTypeCode(),
                        request.householdStatus(),
                        request.primaryBillingAddressId(),
                        tenantId,
                        null,
                        null,
                        null,
                        null,
                        null);
        return householdRepository.save(household);
    }
}

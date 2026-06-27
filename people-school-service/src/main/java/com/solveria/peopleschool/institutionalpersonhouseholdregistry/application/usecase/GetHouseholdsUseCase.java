package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.Household;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.HouseholdRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class GetHouseholdsUseCase {

    private final HouseholdRepositoryPort householdRepository;

    public GetHouseholdsUseCase(HouseholdRepositoryPort householdRepository) {
        this.householdRepository = householdRepository;
    }

    public Page<Household> execute(
            String tenantId, String householdTypeCode, String householdStatus, Pageable pageable) {
        return householdRepository.findAll(tenantId, householdTypeCode, householdStatus, pageable);
    }
}

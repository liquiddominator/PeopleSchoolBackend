package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.Household;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.HouseholdRepositoryPort;

public class GetHouseholdByIdUseCase {

    private final HouseholdRepositoryPort householdRepository;

    public GetHouseholdByIdUseCase(HouseholdRepositoryPort householdRepository) {
        this.householdRepository = householdRepository;
    }

    public Household execute(Long id, String tenantId) {
        return householdRepository
                .findById(id)
                .filter(h -> h.tenantId().equals(tenantId))
                .orElseThrow(() -> new EntityNotFoundException("Household", id.toString()));
    }
}

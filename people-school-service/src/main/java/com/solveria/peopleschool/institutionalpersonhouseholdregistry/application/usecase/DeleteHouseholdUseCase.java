package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.HouseholdRepositoryPort;

public class DeleteHouseholdUseCase {

    private final HouseholdRepositoryPort householdRepository;

    public DeleteHouseholdUseCase(HouseholdRepositoryPort householdRepository) {
        this.householdRepository = householdRepository;
    }

    public void execute(Long id, String tenantId) {
        householdRepository
                .findById(id)
                .filter(h -> h.tenantId().equals(tenantId))
                .orElseThrow(() -> new EntityNotFoundException("Household", id.toString()));
        householdRepository.deleteById(id);
    }
}

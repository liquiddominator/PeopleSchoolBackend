package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.UpdateHouseholdRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.Household;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.HouseholdRepositoryPort;

public class UpdateHouseholdUseCase {

    private final HouseholdRepositoryPort householdRepository;

    public UpdateHouseholdUseCase(HouseholdRepositoryPort householdRepository) {
        this.householdRepository = householdRepository;
    }

    public Household execute(Long id, UpdateHouseholdRequest request, String tenantId) {
        Household existing =
                householdRepository
                        .findById(id)
                        .filter(h -> h.tenantId().equals(tenantId))
                        .orElseThrow(() -> new EntityNotFoundException("Household", id.toString()));

        Household updated =
                new Household(
                        existing.id(),
                        existing.householdCode(),
                        request.householdName(),
                        request.householdTypeCode(),
                        request.householdStatus(),
                        request.primaryBillingAddressId(),
                        existing.tenantId(),
                        existing.createdAt(),
                        existing.createdBy(),
                        null,
                        null,
                        existing.version());
        return householdRepository.save(updated);
    }
}

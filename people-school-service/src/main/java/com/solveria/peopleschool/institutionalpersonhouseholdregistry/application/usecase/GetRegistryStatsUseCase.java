package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.RegistryStatsResponse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.conflict.ConflictStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.conflict.PersonConflictRepositoryPort;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.guardianrelationship.GuardianRelationshipRepositoryPort;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.HouseholdRepositoryPort;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.HouseholdStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.CoreStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.PersonRepositoryPort;
import java.util.List;

public class GetRegistryStatsUseCase {

    private final PersonRepositoryPort personRepository;
    private final HouseholdRepositoryPort householdRepository;
    private final GuardianRelationshipRepositoryPort guardianRelationshipRepository;
    private final PersonConflictRepositoryPort conflictRepository;

    public GetRegistryStatsUseCase(
            PersonRepositoryPort personRepository,
            HouseholdRepositoryPort householdRepository,
            GuardianRelationshipRepositoryPort guardianRelationshipRepository,
            PersonConflictRepositoryPort conflictRepository) {
        this.personRepository = personRepository;
        this.householdRepository = householdRepository;
        this.guardianRelationshipRepository = guardianRelationshipRepository;
        this.conflictRepository = conflictRepository;
    }

    public RegistryStatsResponse execute(String tenantId) {
        long totalActivos =
                personRepository.countByTenantIdAndCoreStatus(tenantId, CoreStatus.ACTIVE);
        long totalHouseholds =
                householdRepository.countByTenantIdAndHouseholdStatus(
                        tenantId, HouseholdStatus.ACTIVE);
        long totalGuardianias = guardianRelationshipRepository.countByTenantId(tenantId);
        long conflictosAbiertos =
                conflictRepository.countByTenantIdAndConflictStatusIn(
                        tenantId, List.of(ConflictStatus.ABIERTO, ConflictStatus.EN_PROCESO));
        return new RegistryStatsResponse(
                totalActivos, totalHouseholds, totalGuardianias, conflictosAbiertos);
    }
}

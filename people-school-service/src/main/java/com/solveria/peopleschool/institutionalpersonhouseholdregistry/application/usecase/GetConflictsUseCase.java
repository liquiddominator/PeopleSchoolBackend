package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.conflict.PersonConflict;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.conflict.PersonConflictRepositoryPort;
import java.util.List;

public class GetConflictsUseCase {

    private final PersonConflictRepositoryPort conflictRepository;

    public GetConflictsUseCase(PersonConflictRepositoryPort conflictRepository) {
        this.conflictRepository = conflictRepository;
    }

    public List<PersonConflict> execute(String tenantId) {
        return conflictRepository.findByTenantId(tenantId);
    }

    public List<PersonConflict> executeByPerson(Long personId, String tenantId) {
        return conflictRepository.findByTenantId(tenantId).stream()
                .filter(
                        conflict ->
                                personId.equals(conflict.personAId())
                                        || personId.equals(conflict.personBId()))
                .toList();
    }
}

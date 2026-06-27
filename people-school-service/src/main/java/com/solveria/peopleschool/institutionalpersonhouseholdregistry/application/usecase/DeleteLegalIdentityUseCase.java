package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.legalidentity.PersonLegalIdentityRepositoryPort;

public class DeleteLegalIdentityUseCase {

    private final PersonLegalIdentityRepositoryPort identityRepository;

    public DeleteLegalIdentityUseCase(PersonLegalIdentityRepositoryPort identityRepository) {
        this.identityRepository = identityRepository;
    }

    public void execute(Long id, String tenantId) {
        identityRepository
                .findById(id)
                .filter(i -> i.tenantId().equals(tenantId))
                .orElseThrow(
                        () -> new EntityNotFoundException("PersonLegalIdentity", id.toString()));
        identityRepository.deleteById(id);
    }
}

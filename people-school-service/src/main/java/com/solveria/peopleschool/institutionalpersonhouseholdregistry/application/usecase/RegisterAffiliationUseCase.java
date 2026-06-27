package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreateAffiliationRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalaffiliation.AffiliationStatus;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalaffiliation.InstitutionalAffiliation;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalaffiliation.InstitutionalAffiliationRepositoryPort;
import java.time.LocalDate;

public class RegisterAffiliationUseCase {

    private final InstitutionalAffiliationRepositoryPort repository;

    public RegisterAffiliationUseCase(InstitutionalAffiliationRepositoryPort repository) {
        this.repository = repository;
    }

    public InstitutionalAffiliation execute(CreateAffiliationRequest request, String tenantId) {
        InstitutionalAffiliation affiliation =
                new InstitutionalAffiliation(
                        null,
                        request.personId(),
                        request.affiliationTypeCode(),
                        request.siteId(),
                        request.organizationalUnitId(),
                        AffiliationStatus.valueOf(request.affiliationStatus()),
                        request.effectiveFrom() != null
                                ? LocalDate.parse(request.effectiveFrom())
                                : null,
                        request.effectiveTo() != null
                                ? LocalDate.parse(request.effectiveTo())
                                : null,
                        tenantId,
                        null,
                        null,
                        null,
                        null,
                        null);
        return repository.save(affiliation);
    }
}

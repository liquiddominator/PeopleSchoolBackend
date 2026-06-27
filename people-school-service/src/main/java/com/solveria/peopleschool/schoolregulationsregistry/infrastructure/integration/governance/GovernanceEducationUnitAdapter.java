package com.solveria.peopleschool.schoolregulationsregistry.infrastructure.integration.governance;

import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnitRepositoryPort;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.UnitStatus;
import com.solveria.peopleschool.schoolregulationsregistry.application.port.out.EducationUnitReferencePort;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class GovernanceEducationUnitAdapter implements EducationUnitReferencePort {

    private final EducationUnitRepositoryPort educationUnitRepository;

    public GovernanceEducationUnitAdapter(EducationUnitRepositoryPort educationUnitRepository) {
        this.educationUnitRepository = educationUnitRepository;
    }

    @Override
    public Optional<EducationUnitReference> findByTenantId(String tenantId) {
        return educationUnitRepository
                .findByTenantId(tenantId)
                .map(
                        unit ->
                                new EducationUnitReference(
                                        unit.id(), unit.unitStatus() == UnitStatus.ACTIVE));
    }
}

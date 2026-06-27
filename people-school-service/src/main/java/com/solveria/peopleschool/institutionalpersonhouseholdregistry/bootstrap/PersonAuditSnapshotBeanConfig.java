package com.solveria.peopleschool.institutionalpersonhouseholdregistry.bootstrap;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetAuditSnapshotsUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personauditsnapshot.PersonAuditSnapshotRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonAuditSnapshotBeanConfig {

    @Bean
    public GetAuditSnapshotsUseCase getAuditSnapshotsUseCase(
            PersonAuditSnapshotRepositoryPort repository) {
        return new GetAuditSnapshotsUseCase(repository);
    }
}

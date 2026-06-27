package com.solveria.peopleschool.governanceregistry.bootstrap;

import com.solveria.peopleschool.governanceregistry.application.usecase.CreateOrganizationalUnitUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.DeleteOrganizationalUnitUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.GetOrganizationalUnitsUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.UpdateOrganizationalUnitUseCase;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnitRepositoryPort;
import com.solveria.peopleschool.governanceregistry.domain.orgunit.OrganizationalUnitRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrganizationalUnitBeanConfig {

    @Bean
    public CreateOrganizationalUnitUseCase createOrganizationalUnitUseCase(
            OrganizationalUnitRepositoryPort repo, EducationUnitRepositoryPort eduRepo) {
        return new CreateOrganizationalUnitUseCase(repo, eduRepo);
    }

    @Bean
    public GetOrganizationalUnitsUseCase getOrganizationalUnitsUseCase(
            OrganizationalUnitRepositoryPort repo) {
        return new GetOrganizationalUnitsUseCase(repo);
    }

    @Bean
    public UpdateOrganizationalUnitUseCase updateOrganizationalUnitUseCase(
            OrganizationalUnitRepositoryPort repo) {
        return new UpdateOrganizationalUnitUseCase(repo);
    }

    @Bean
    public DeleteOrganizationalUnitUseCase deleteOrganizationalUnitUseCase(
            OrganizationalUnitRepositoryPort repo) {
        return new DeleteOrganizationalUnitUseCase(repo);
    }
}

package com.solveria.peopleschool.governanceregistry.bootstrap;

import com.solveria.peopleschool.governanceregistry.application.usecase.CreateRoleCatalogUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.DeleteRoleCatalogUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.GetRoleCatalogUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.UpdateRoleCatalogUseCase;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnitRepositoryPort;
import com.solveria.peopleschool.governanceregistry.domain.rolecatalog.RoleCatalogRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleCatalogBeanConfig {

    @Bean
    public CreateRoleCatalogUseCase createRoleCatalogUseCase(
            RoleCatalogRepositoryPort repo, EducationUnitRepositoryPort eduRepo) {
        return new CreateRoleCatalogUseCase(repo, eduRepo);
    }

    @Bean
    public GetRoleCatalogUseCase getRoleCatalogUseCase(RoleCatalogRepositoryPort repo) {
        return new GetRoleCatalogUseCase(repo);
    }

    @Bean
    public UpdateRoleCatalogUseCase updateRoleCatalogUseCase(RoleCatalogRepositoryPort repo) {
        return new UpdateRoleCatalogUseCase(repo);
    }

    @Bean
    public DeleteRoleCatalogUseCase deleteRoleCatalogUseCase(RoleCatalogRepositoryPort repo) {
        return new DeleteRoleCatalogUseCase(repo);
    }
}

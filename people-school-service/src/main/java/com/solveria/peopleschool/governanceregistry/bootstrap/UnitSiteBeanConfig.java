package com.solveria.peopleschool.governanceregistry.bootstrap;

import com.solveria.peopleschool.governanceregistry.application.usecase.CreateUnitSiteUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.DeleteUnitSiteUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.GetUnitSitesUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.UpdateUnitSiteUseCase;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnitRepositoryPort;
import com.solveria.peopleschool.governanceregistry.domain.unitsite.UnitSiteRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UnitSiteBeanConfig {

    @Bean
    public CreateUnitSiteUseCase createUnitSiteUseCase(
            UnitSiteRepositoryPort repository,
            EducationUnitRepositoryPort educationUnitRepository) {
        return new CreateUnitSiteUseCase(repository, educationUnitRepository);
    }

    @Bean
    public GetUnitSitesUseCase getUnitSitesUseCase(UnitSiteRepositoryPort repository) {
        return new GetUnitSitesUseCase(repository);
    }

    @Bean
    public UpdateUnitSiteUseCase updateUnitSiteUseCase(UnitSiteRepositoryPort repository) {
        return new UpdateUnitSiteUseCase(repository);
    }

    @Bean
    public DeleteUnitSiteUseCase deleteUnitSiteUseCase(UnitSiteRepositoryPort repository) {
        return new DeleteUnitSiteUseCase(repository);
    }
}

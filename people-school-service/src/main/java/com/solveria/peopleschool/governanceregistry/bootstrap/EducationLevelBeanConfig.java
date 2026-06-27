package com.solveria.peopleschool.governanceregistry.bootstrap;

import com.solveria.peopleschool.governanceregistry.application.usecase.CreateEducationLevelUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.DeleteEducationLevelUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.GetEducationLevelsUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.UpdateEducationLevelUseCase;
import com.solveria.peopleschool.governanceregistry.domain.educationlevel.EducationLevelRepositoryPort;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnitRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EducationLevelBeanConfig {

    @Bean
    public CreateEducationLevelUseCase createEducationLevelUseCase(
            EducationLevelRepositoryPort repo, EducationUnitRepositoryPort eduRepo) {
        return new CreateEducationLevelUseCase(repo, eduRepo);
    }

    @Bean
    public GetEducationLevelsUseCase getEducationLevelsUseCase(EducationLevelRepositoryPort repo) {
        return new GetEducationLevelsUseCase(repo);
    }

    @Bean
    public UpdateEducationLevelUseCase updateEducationLevelUseCase(
            EducationLevelRepositoryPort repo) {
        return new UpdateEducationLevelUseCase(repo);
    }

    @Bean
    public DeleteEducationLevelUseCase deleteEducationLevelUseCase(
            EducationLevelRepositoryPort repo) {
        return new DeleteEducationLevelUseCase(repo);
    }
}

package com.solveria.peopleschool.schoolregulationsregistry.bootstrap;

import com.solveria.peopleschool.schoolregulationsregistry.application.port.out.EducationUnitReferencePort;
import com.solveria.peopleschool.schoolregulationsregistry.application.usecase.CreateSchoolRegulationUseCase;
import com.solveria.peopleschool.schoolregulationsregistry.application.usecase.DeleteSchoolRegulationUseCase;
import com.solveria.peopleschool.schoolregulationsregistry.application.usecase.GetSchoolRegulationsUseCase;
import com.solveria.peopleschool.schoolregulationsregistry.application.usecase.UpdateSchoolRegulationUseCase;
import com.solveria.peopleschool.schoolregulationsregistry.domain.schoolregulation.SchoolRegulationRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchoolRegulationBeanConfig {

    @Bean
    public CreateSchoolRegulationUseCase createSchoolRegulationUseCase(
            SchoolRegulationRepositoryPort repo,
            EducationUnitReferencePort educationUnitReferencePort) {
        return new CreateSchoolRegulationUseCase(repo, educationUnitReferencePort);
    }

    @Bean
    public GetSchoolRegulationsUseCase getSchoolRegulationsUseCase(
            SchoolRegulationRepositoryPort repo) {
        return new GetSchoolRegulationsUseCase(repo);
    }

    @Bean
    public UpdateSchoolRegulationUseCase updateSchoolRegulationUseCase(
            SchoolRegulationRepositoryPort repo) {
        return new UpdateSchoolRegulationUseCase(repo);
    }

    @Bean
    public DeleteSchoolRegulationUseCase deleteSchoolRegulationUseCase(
            SchoolRegulationRepositoryPort repo) {
        return new DeleteSchoolRegulationUseCase(repo);
    }
}

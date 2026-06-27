package com.solveria.peopleschool.governanceregistry.bootstrap;

import com.solveria.peopleschool.governanceregistry.application.usecase.ActivateSchoolYearUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.CreateSchoolYearUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.DeleteSchoolYearUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.GetSchoolYearsUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.UpdateSchoolYearUseCase;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnitRepositoryPort;
import com.solveria.peopleschool.governanceregistry.domain.schoolyear.SchoolYearRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchoolYearBeanConfig {

    @Bean
    public CreateSchoolYearUseCase createSchoolYearUseCase(
            SchoolYearRepositoryPort repo, EducationUnitRepositoryPort eduRepo) {
        return new CreateSchoolYearUseCase(repo, eduRepo);
    }

    @Bean
    public GetSchoolYearsUseCase getSchoolYearsUseCase(SchoolYearRepositoryPort repo) {
        return new GetSchoolYearsUseCase(repo);
    }

    @Bean
    public UpdateSchoolYearUseCase updateSchoolYearUseCase(SchoolYearRepositoryPort repo) {
        return new UpdateSchoolYearUseCase(repo);
    }

    @Bean
    public ActivateSchoolYearUseCase activateSchoolYearUseCase(SchoolYearRepositoryPort repo) {
        return new ActivateSchoolYearUseCase(repo);
    }

    @Bean
    public DeleteSchoolYearUseCase deleteSchoolYearUseCase(SchoolYearRepositoryPort repo) {
        return new DeleteSchoolYearUseCase(repo);
    }
}

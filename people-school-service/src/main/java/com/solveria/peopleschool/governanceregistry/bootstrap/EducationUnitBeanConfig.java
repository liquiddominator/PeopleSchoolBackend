package com.solveria.peopleschool.governanceregistry.bootstrap;

import com.solveria.peopleschool.governanceregistry.application.usecase.GetCurrentEducationUnitUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.UpdateEducationUnitUseCase;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnitRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EducationUnitBeanConfig {

    @Bean
    public GetCurrentEducationUnitUseCase getCurrentEducationUnitUseCase(
            EducationUnitRepositoryPort repo) {
        return new GetCurrentEducationUnitUseCase(repo);
    }

    @Bean
    public UpdateEducationUnitUseCase updateEducationUnitUseCase(EducationUnitRepositoryPort repo) {
        return new UpdateEducationUnitUseCase(repo);
    }
}

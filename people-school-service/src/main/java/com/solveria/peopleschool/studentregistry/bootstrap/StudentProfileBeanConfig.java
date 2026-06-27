package com.solveria.peopleschool.studentregistry.bootstrap;

import com.solveria.peopleschool.studentregistry.application.usecase.CreateStudentProfileUseCase;
import com.solveria.peopleschool.studentregistry.application.usecase.GetStudentProfilesUseCase;
import com.solveria.peopleschool.studentregistry.domain.studentprofile.StudentProfileRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentProfileBeanConfig {

    @Bean
    public CreateStudentProfileUseCase createStudentProfileUseCase(
            StudentProfileRepositoryPort repo) {
        return new CreateStudentProfileUseCase(repo);
    }

    @Bean
    public GetStudentProfilesUseCase getStudentProfilesUseCase(StudentProfileRepositoryPort repo) {
        return new GetStudentProfilesUseCase(repo);
    }
}

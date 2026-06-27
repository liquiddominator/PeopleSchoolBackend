package com.solveria.peopleschool.governanceregistry.bootstrap;

import com.solveria.peopleschool.governanceregistry.application.usecase.CreateAppointmentCatalogUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.DeleteAppointmentCatalogUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.GetAppointmentCatalogUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.UpdateAppointmentCatalogUseCase;
import com.solveria.peopleschool.governanceregistry.domain.appointmentcatalog.AppointmentCatalogRepositoryPort;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnitRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppointmentCatalogBeanConfig {

    @Bean
    public CreateAppointmentCatalogUseCase createAppointmentCatalogUseCase(
            AppointmentCatalogRepositoryPort repo, EducationUnitRepositoryPort eduRepo) {
        return new CreateAppointmentCatalogUseCase(repo, eduRepo);
    }

    @Bean
    public GetAppointmentCatalogUseCase getAppointmentCatalogUseCase(
            AppointmentCatalogRepositoryPort repo) {
        return new GetAppointmentCatalogUseCase(repo);
    }

    @Bean
    public UpdateAppointmentCatalogUseCase updateAppointmentCatalogUseCase(
            AppointmentCatalogRepositoryPort repo) {
        return new UpdateAppointmentCatalogUseCase(repo);
    }

    @Bean
    public DeleteAppointmentCatalogUseCase deleteAppointmentCatalogUseCase(
            AppointmentCatalogRepositoryPort repo) {
        return new DeleteAppointmentCatalogUseCase(repo);
    }
}

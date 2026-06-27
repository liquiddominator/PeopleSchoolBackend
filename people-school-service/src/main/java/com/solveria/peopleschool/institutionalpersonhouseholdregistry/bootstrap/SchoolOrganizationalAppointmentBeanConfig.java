package com.solveria.peopleschool.institutionalpersonhouseholdregistry.bootstrap;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.CreateSchoolOrganizationalAppointmentUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetAppointmentsUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.schoolorganizationalappointment.SchoolOrganizationalAppointmentRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchoolOrganizationalAppointmentBeanConfig {

    @Bean
    public CreateSchoolOrganizationalAppointmentUseCase
            createSchoolOrganizationalAppointmentUseCase(
                    SchoolOrganizationalAppointmentRepositoryPort repository) {
        return new CreateSchoolOrganizationalAppointmentUseCase(repository);
    }

    @Bean
    public GetAppointmentsUseCase getAppointmentsUseCase(
            SchoolOrganizationalAppointmentRepositoryPort repository) {
        return new GetAppointmentsUseCase(repository);
    }
}

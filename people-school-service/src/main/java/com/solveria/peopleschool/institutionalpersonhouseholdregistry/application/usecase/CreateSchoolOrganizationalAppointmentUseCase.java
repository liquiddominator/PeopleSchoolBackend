package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreateAppointmentRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.schoolorganizationalappointment.SchoolOrganizationalAppointment;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.schoolorganizationalappointment.SchoolOrganizationalAppointmentRepositoryPort;

public class CreateSchoolOrganizationalAppointmentUseCase {

    private final SchoolOrganizationalAppointmentRepositoryPort repository;

    public CreateSchoolOrganizationalAppointmentUseCase(
            SchoolOrganizationalAppointmentRepositoryPort repository) {
        this.repository = repository;
    }

    public SchoolOrganizationalAppointment execute(
            CreateAppointmentRequest request, String tenantId) {
        SchoolOrganizationalAppointment appointment =
                new SchoolOrganizationalAppointment(
                        null,
                        request.personId(),
                        request.appointmentCode(),
                        request.organizationalUnitId(),
                        request.appointmentStatus(),
                        request.startedAt(),
                        request.endedAt(),
                        tenantId,
                        null,
                        null,
                        null,
                        null,
                        null);
        return repository.save(appointment);
    }
}

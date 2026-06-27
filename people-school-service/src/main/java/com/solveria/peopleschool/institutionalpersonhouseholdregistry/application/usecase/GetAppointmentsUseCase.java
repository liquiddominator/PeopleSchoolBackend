package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.schoolorganizationalappointment.SchoolOrganizationalAppointment;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.schoolorganizationalappointment.SchoolOrganizationalAppointmentRepositoryPort;
import java.util.List;

public class GetAppointmentsUseCase {

    private final SchoolOrganizationalAppointmentRepositoryPort repository;

    public GetAppointmentsUseCase(SchoolOrganizationalAppointmentRepositoryPort repository) {
        this.repository = repository;
    }

    public List<SchoolOrganizationalAppointment> execute(Long personId, String tenantId) {
        return repository.findAllByPersonIdAndTenantId(personId, tenantId);
    }
}

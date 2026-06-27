package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.schoolorganizationalappointment;

import java.util.List;
import java.util.Optional;

public interface SchoolOrganizationalAppointmentRepositoryPort {
    SchoolOrganizationalAppointment save(SchoolOrganizationalAppointment appointment);

    Optional<SchoolOrganizationalAppointment> findById(Long id);

    List<SchoolOrganizationalAppointment> findAllByPersonIdAndTenantId(
            Long personId, String tenantId);

    void deleteById(Long id);
}

package com.solveria.peopleschool.institutionalpersonhouseholdregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.schoolorganizationalappointment.SchoolOrganizationalAppointment;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.schoolorganizationalappointment.SchoolOrganizationalAppointmentRepositoryPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchoolOrganizationalAppointmentPersistenceAdapter
        implements SchoolOrganizationalAppointmentRepositoryPort {

    private final SchoolOrganizationalAppointmentJpaRepository repository;
    private final SchoolOrganizationalAppointmentMapper mapper;

    @Override
    public SchoolOrganizationalAppointment save(SchoolOrganizationalAppointment domain) {
        SchoolOrganizationalAppointmentJpaEntity entity;
        if (domain.id() != null) {
            entity = repository.findById(domain.id()).orElseThrow();
        } else {
            entity = mapper.toNewEntity(domain);
        }
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<SchoolOrganizationalAppointment> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<SchoolOrganizationalAppointment> findAllByPersonIdAndTenantId(
            Long personId, String tenantId) {
        return repository.findAllByPersonIdAndTenantId(personId, tenantId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

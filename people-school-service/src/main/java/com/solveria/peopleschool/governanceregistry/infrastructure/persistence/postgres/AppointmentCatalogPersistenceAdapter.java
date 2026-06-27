package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.exceptions.EntityNotFoundException;
import com.solveria.peopleschool.governanceregistry.domain.appointmentcatalog.AppointmentCatalog;
import com.solveria.peopleschool.governanceregistry.domain.appointmentcatalog.AppointmentCatalogRepositoryPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppointmentCatalogPersistenceAdapter implements AppointmentCatalogRepositoryPort {

    private final AppointmentCatalogJpaRepository repository;
    private final AppointmentCatalogMapper mapper;

    @Override
    public AppointmentCatalog save(AppointmentCatalog domain) {
        AppointmentCatalogJpaEntity entity;
        if (domain.id() != null) {
            entity =
                    repository
                            .findById(domain.id())
                            .orElseThrow(
                                    () ->
                                            new EntityNotFoundException(
                                                    "AppointmentCatalog", domain.id().toString()));
            mapper.updateEntity(entity, domain);
        } else {
            entity = mapper.toNewEntity(domain);
        }
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<AppointmentCatalog> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<AppointmentCatalog> findAllByTenantId(String tenantId) {
        return repository.findAllByTenantIdOrderByDisplayOrderAsc(tenantId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsByAppointmentCodeAndTenantId(String code, String tenantId) {
        return repository.existsByAppointmentCodeAndTenantId(code, tenantId);
    }
}

package com.solveria.iamservice.config;

import com.solveria.core.iam.application.port.PermissionRepositoryPort;
import com.solveria.core.iam.application.port.RoleRepositoryPort;
import com.solveria.core.iam.application.usecase.AssignPermissionsToRoleUseCase;
import com.solveria.core.iam.application.usecase.CreateRoleUseCase;
import com.solveria.iamservice.application.orchestration.AssignPermissionsToRoleOrchestrator;
import com.solveria.iamservice.application.orchestration.CreateRoleOrchestrator;
import com.solveria.iamservice.application.service.AuditTrailService;
import com.solveria.iamservice.infrastructure.persistence.repository.TenantPermissionRepository;
import com.solveria.iamservice.infrastructure.persistence.repository.TenantRoleRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for wiring UseCases and Orchestrators.
 *
 * <p>Explicitly creates beans for: - UseCases from core-platform (injecting Ports from adapters) -
 * Orchestrators from iam-service (injecting UseCases)
 *
 * <p>Uses @ConditionalOnMissingBean to allow overrides in future extraction phases. This enables
 * gradual migration: new implementations can be provided without breaking existing wiring.
 */
@Configuration
public class UseCaseConfig {

    // UseCase beans (from core-platform)

    @Bean
    @ConditionalOnMissingBean(CreateRoleUseCase.class)
    public CreateRoleUseCase createRoleUseCase(RoleRepositoryPort roleRepositoryPort) {
        return new CreateRoleUseCase(roleRepositoryPort);
    }

    @Bean
    @ConditionalOnMissingBean(AssignPermissionsToRoleUseCase.class)
    public AssignPermissionsToRoleUseCase assignPermissionsToRoleUseCase(
            RoleRepositoryPort roleRepositoryPort,
            PermissionRepositoryPort permissionRepositoryPort) {
        return new AssignPermissionsToRoleUseCase(roleRepositoryPort, permissionRepositoryPort);
    }

    // Orchestrator beans (from iam-service)

    @Bean
    @ConditionalOnMissingBean(CreateRoleOrchestrator.class)
    public CreateRoleOrchestrator createRoleOrchestrator(CreateRoleUseCase createRoleUseCase) {
        return new CreateRoleOrchestrator(createRoleUseCase);
    }

    @Bean
    @ConditionalOnMissingBean(AssignPermissionsToRoleOrchestrator.class)
    public AssignPermissionsToRoleOrchestrator assignPermissionsToRoleOrchestrator(
            AssignPermissionsToRoleUseCase assignPermissionsToRoleUseCase,
            TenantRoleRepository tenantRoleRepository,
            TenantPermissionRepository tenantPermissionRepository,
            AuditTrailService auditTrailService) {
        return new AssignPermissionsToRoleOrchestrator(
                assignPermissionsToRoleUseCase,
                tenantRoleRepository,
                tenantPermissionRepository,
                auditTrailService);
    }
}

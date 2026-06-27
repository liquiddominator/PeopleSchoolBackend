package com.solveria.peopleschool.institutionalpersonhouseholdregistry.bootstrap;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.AssignInstitutionalRoleUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetRoleAssignmentsUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.institutionalroleassignment.InstitutionalRoleAssignmentRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InstitutionalRoleAssignmentBeanConfig {

    @Bean
    public AssignInstitutionalRoleUseCase assignInstitutionalRoleUseCase(
            InstitutionalRoleAssignmentRepositoryPort repository) {
        return new AssignInstitutionalRoleUseCase(repository);
    }

    @Bean
    public GetRoleAssignmentsUseCase getRoleAssignmentsUseCase(
            InstitutionalRoleAssignmentRepositoryPort repository) {
        return new GetRoleAssignmentsUseCase(repository);
    }
}

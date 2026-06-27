package com.solveria.peopleschool.governanceregistry.bootstrap;

import com.solveria.peopleschool.governanceregistry.application.usecase.CreateOrgGroupUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.GetCurrentOrgGroupUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.UpdateOrgGroupUseCase;
import com.solveria.peopleschool.governanceregistry.domain.orggroup.OrgGroupRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrgGroupBeanConfig {

    @Bean
    public CreateOrgGroupUseCase createOrgGroupUseCase(OrgGroupRepositoryPort repository) {
        return new CreateOrgGroupUseCase(repository);
    }

    @Bean
    public GetCurrentOrgGroupUseCase getCurrentOrgGroupUseCase(OrgGroupRepositoryPort repository) {
        return new GetCurrentOrgGroupUseCase(repository);
    }

    @Bean
    public UpdateOrgGroupUseCase updateOrgGroupUseCase(OrgGroupRepositoryPort repository) {
        return new UpdateOrgGroupUseCase(repository);
    }
}

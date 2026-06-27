package com.solveria.peopleschool.institutionalpersonhouseholdregistry.bootstrap;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetFinancialResponsiblesUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.RegisterFinancialResponsibleUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.financialresponsiblerelationship.FinancialResponsibleRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FinancialResponsibleBeanConfig {

    @Bean
    public RegisterFinancialResponsibleUseCase registerFinancialResponsibleUseCase(
            FinancialResponsibleRepositoryPort repository) {
        return new RegisterFinancialResponsibleUseCase(repository);
    }

    @Bean
    public GetFinancialResponsiblesUseCase getFinancialResponsiblesUseCase(
            FinancialResponsibleRepositoryPort repository) {
        return new GetFinancialResponsiblesUseCase(repository);
    }
}

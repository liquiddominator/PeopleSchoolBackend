package com.solveria.iamservice;

import com.solveria.iamservice.config.security.JwtProperties;
import com.solveria.iamservice.config.security.TokenProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(
        basePackages = {
            "com.solveria.core.iam.infrastructure.persistence.entity",
            "com.solveria.iamservice.infrastructure.persistence.entity"
        })
@EnableJpaRepositories(
        basePackages = {
            "com.solveria.core.iam.infrastructure.persistence.repository",
            "com.solveria.iamservice.infrastructure.persistence.repository"
        })
@EnableConfigurationProperties({JwtProperties.class, TokenProperties.class})
public class IamServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IamServiceApplication.class, args);
    }
}

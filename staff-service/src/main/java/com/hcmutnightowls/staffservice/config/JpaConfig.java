package com.hcmutnightowls.staffservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.hcmutnightowls.staffservice.repository")
@EnableTransactionManagement
public class JpaConfig {
    // JPA-specific configuration can be added here if needed
}
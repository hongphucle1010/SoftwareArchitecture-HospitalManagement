package com.hcmutnightowls.patientservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI patientServiceAPI() {
        return new OpenAPI().info(new Info()
                .title("Patient Service API")
                .description("API documentation for the Patient Service")
                .version("v0.0.1"));

    }
}

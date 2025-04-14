package com.hcmutnightowls.testingservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI testingServiceAPI() {
        return new OpenAPI().info(new Info()
                .title("Testing Service API")
                .description("API documentation for the Testing Service")
                .version("v0.0.1"));

    }
}

package com.hcmutnightowls.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI apiGatewayAPI() {
        return new OpenAPI().info(new Info()
                .title("API Gateway API")
                .description("API documentation for all services")
                .version("v0.0.1"));
    }
}

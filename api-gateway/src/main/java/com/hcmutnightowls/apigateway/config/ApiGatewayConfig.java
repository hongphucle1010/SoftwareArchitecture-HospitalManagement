package com.hcmutnightowls.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.server.WebSessionServerOAuth2AuthorizedClientRepository;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public ServerOAuth2AuthorizedClientRepository authorizedClientRepository() {
        return new WebSessionServerOAuth2AuthorizedClientRepository();
    }

    @Bean
    public WebClient webClient(ReactiveClientRegistrationRepository clientRegistrations) {
        return WebClient.builder().build();
    }

    @Bean
    public HttpStatusServerEntryPoint httpStatusServerEntryPoint() {
        // Return 401 instead of redirect to login page for API calls
        return new HttpStatusServerEntryPoint(HttpStatus.UNAUTHORIZED);
    }
}
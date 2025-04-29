package com.hcmutnightowls.apigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class TokenRelayFilter implements GlobalFilter {

    private final ServerOAuth2AuthorizedClientRepository authorizedClientRepository;

    public TokenRelayFilter(ServerOAuth2AuthorizedClientRepository authorizedClientRepository) {
        this.authorizedClientRepository = authorizedClientRepository;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return ReactiveSecurityContextHolder.getContext()
                .filter(ctx -> ctx.getAuthentication() != null)
                .map(SecurityContext::getAuthentication)
                .filter(auth -> auth instanceof OAuth2AuthenticationToken)
                .cast(OAuth2AuthenticationToken.class)
                .flatMap(authentication -> authorizedClientRepository
                        .loadAuthorizedClient(
                                authentication.getAuthorizedClientRegistrationId(),
                                authentication,
                                exchange)
                        .map(client -> withBearerAuth(exchange, authentication, client)))
                .defaultIfEmpty(exchange)
                .flatMap(chain::filter);
    }

    private ServerWebExchange withBearerAuth(ServerWebExchange exchange, 
                                           Authentication authentication, 
                                           OAuth2AuthorizedClient client) {
        return exchange.mutate()
                .request(r -> r.headers(headers -> headers.add(
                        HttpHeaders.AUTHORIZATION,
                        "Bearer " + client.getAccessToken().getTokenValue())))
                .build();
    }
}
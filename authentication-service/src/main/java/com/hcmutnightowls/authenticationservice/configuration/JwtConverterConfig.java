package com.hcmutnightowls.authenticationservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class JwtConverterConfig {

    @Bean
    public Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter());
        return jwtAuthenticationConverter;
    }

    @Bean
    public Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter() {
        JwtGrantedAuthoritiesConverter defaultConverter = new JwtGrantedAuthoritiesConverter();

        return jwt -> {
            Collection<GrantedAuthority> defaultAuthorities = defaultConverter.convert(jwt);
            
            // Extract scopes from the JWT claims if available
            Map<String, Object> claims = jwt.getClaims();
            
            List<String> scopes = extractScopes(claims);
            List<GrantedAuthority> scopeAuthorities = scopes.stream()
                    .map(scope -> new SimpleGrantedAuthority(scope))
                    .collect(Collectors.toList());
            
            return Stream.concat(
                    defaultAuthorities != null ? defaultAuthorities.stream() : Stream.empty(),
                    scopeAuthorities.stream()
            ).collect(Collectors.toList());
        };
    }
    
    @SuppressWarnings("unchecked")
    private List<String> extractScopes(Map<String, Object> claims) {
        Object scopeObj = claims.get("scope");
        if (scopeObj instanceof String) {
            return List.of(((String) scopeObj).split(" "));
        } else if (scopeObj instanceof Collection) {
            return ((Collection<String>) scopeObj).stream()
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}

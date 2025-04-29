package com.hcmutnightowls.authenticationservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.proc.SecurityContext;

import java.util.Base64;

@Configuration
public class JwtEncoderConfig {

    @Value("${jwt.secret_key}")
    private String secretKey;

    @Bean
    public JwtEncoder jwtEncoder() {
        byte[] decodedSecret = Base64.getDecoder().decode(secretKey);
        ImmutableSecret<SecurityContext> secret = new ImmutableSecret<>(decodedSecret);
        
        return new NimbusJwtEncoder(secret);
    }
}
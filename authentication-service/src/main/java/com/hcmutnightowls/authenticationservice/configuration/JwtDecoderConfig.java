package com.hcmutnightowls.authenticationservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import java.util.Base64;

@Configuration
public class JwtDecoderConfig {

    @Value("${jwt.secret_key}")
    private String secretKey;

    @Bean
    public JwtDecoder jwtDecoder() {
        byte[] decodedSecret = Base64.getDecoder().decode(secretKey);
        return NimbusJwtDecoder.withSecretKey(new javax.crypto.spec.SecretKeySpec(decodedSecret, "HmacSHA256"))
                .build();
    }
}

package com.hcmutnightowls.patientservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Configuration
public class JwtDecoderConfig {

    @Value("${jwt.secret_key}")
    private String secretKey;
    @Bean
    public JwtDecoder jwtDecoder() {
        byte[] decodedSecret = Base64.getDecoder().decode(secretKey);
        SecretKeySpec secretKeySpec = new SecretKeySpec(decodedSecret, "HmacSHA256");

        return NimbusJwtDecoder.withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }
}

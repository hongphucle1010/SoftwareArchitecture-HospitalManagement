package com.hcmutnightowls.staffservice.configuration;

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

    private final String secretKey = "bXktdmVyeS1sb25nLWFuZC1zZWN1cmUtc2VjcmV0LWtleS13aGljaC1pcy1hdC1sZWFzdC0zMi1ieXRlcw==";

    @Bean
    public JwtDecoder jwtDecoder() {
        byte[] decodedSecret = Base64.getDecoder().decode(secretKey);
        SecretKeySpec secretKeySpec = new SecretKeySpec(decodedSecret, "HmacSHA256");

        return NimbusJwtDecoder.withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }
}

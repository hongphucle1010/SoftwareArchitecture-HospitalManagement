package com.hcmutnightowls.authenticationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenService {

    private final JwtEncoder jwtEncoder;

    public String generateToken(String subject, List<String> scopes, Long expirationTime) {
        Instant now = Instant.now();
        
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("https://hospital-management-system")
                .issuedAt(now)
                .expiresAt(now.plus(expirationTime, ChronoUnit.SECONDS))
                .subject(subject)
                .claim("scope", scopes)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String generateAccessToken(String username, List<GrantedAuthority> authorities) {
        List<String> scopes = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return generateToken(username, scopes, 3600L); // 1 hour
    }

    public String generateRefreshToken(String username) {
        return generateToken(username, List.of("REFRESH_TOKEN"), 86400L); // 24 hours
    }
}
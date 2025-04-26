package com.hcmutnightowls.authenticationservice.component;

import com.hcmutnightowls.authenticationservice.model.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import org.springframework.beans.factory.annotation.Value;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtAuthen {
    @Value("${jwt.secret_key}")
    String secret;
    @Value("${jwt.expirationtime}")
    long expiration;

    public String createJWT(String id, String subject, String role, long ttlMillis) {
        // The JWT signature algorithm we will be using to sign the token
        // this is header
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // We will sign our JWT with our ApiKey secret
        // this is signature
        byte[] apiKeySecretBytes = java.util.Base64.getDecoder().decode(secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // Let's set the JWT Claims
        // this is payload
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .claim("role", role)
                .signWith(signingKey, signatureAlgorithm);

        // If it has been specified, let's add the expiration
        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }
    public String authentication(String userPassword, User user) {
        if (BCrypt.checkpw(userPassword, user.getPassword())) {
            // Password matches, return a success message or token
            return createJWT(String.valueOf(user.getId()), user.getSubject(), user.getRole(), expiration);
        } else {
            // Password does not match, throw an exception or return an error message
            throw new RuntimeException("Invalid password");
        }
    }
}

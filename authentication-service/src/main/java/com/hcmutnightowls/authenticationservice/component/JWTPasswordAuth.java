package com.hcmutnightowls.authenticationservice.component;

import com.hcmutnightowls.authenticationservice.model.User;
import com.hcmutnightowls.authenticationservice.service.Interface.JWT.IJWTPasswordAuth;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JWTPasswordAuth implements IJWTPasswordAuth {
    public boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
    @Override
    public void verifyOrThrow(String rawPassword, User user) {
        if (!verifyPassword(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
    }
}

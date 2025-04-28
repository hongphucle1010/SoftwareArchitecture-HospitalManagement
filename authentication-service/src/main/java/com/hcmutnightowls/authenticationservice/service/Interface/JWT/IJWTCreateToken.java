package com.hcmutnightowls.authenticationservice.service.Interface.JWT;

public interface IJWTCreateToken {
    public String createJWT(String id, String subject, String role, long ttlMillis);
}

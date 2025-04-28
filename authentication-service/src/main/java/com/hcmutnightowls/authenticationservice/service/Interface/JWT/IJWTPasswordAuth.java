package com.hcmutnightowls.authenticationservice.service.Interface.JWT;

import com.hcmutnightowls.authenticationservice.model.User;

public interface IJWTPasswordAuth {
    public void verifyOrThrow(String rawPassword, User user);
}

package com.hcmutnightowls.authenticationservice.service.Interface.Strategy;

import com.hcmutnightowls.authenticationservice.dto.request.request;

public interface UserLoginStrategy {
    String login(request loginRequest);
}

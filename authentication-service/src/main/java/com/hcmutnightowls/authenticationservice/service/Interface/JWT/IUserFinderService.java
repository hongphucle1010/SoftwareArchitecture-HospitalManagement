package com.hcmutnightowls.authenticationservice.service.Interface.JWT;

import com.hcmutnightowls.authenticationservice.dto.request.request;

public interface IUserFinderService {
    public <T> T findUser(String userType, request loginRequest);
}

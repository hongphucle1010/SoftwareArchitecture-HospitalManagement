package com.hcmutnightowls.authenticationservice.service;

import com.hcmutnightowls.authenticationservice.dto.request.request;
import com.hcmutnightowls.authenticationservice.service.Interface.Provider.LoginStrategyProvider;
import com.hcmutnightowls.authenticationservice.service.Interface.Strategy.UserLoginStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
// create autowired constructor for final fields
public class AuthService {
    private final LoginStrategyProvider loginStrategyProvider;

    public String login(String userType, request loginRequest) {
        UserLoginStrategy strategy = loginStrategyProvider.getStrategy(userType);
        return strategy.login(loginRequest);
    }
}

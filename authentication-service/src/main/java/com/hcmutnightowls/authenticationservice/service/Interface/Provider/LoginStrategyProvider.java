package com.hcmutnightowls.authenticationservice.service.Interface.Provider;

import com.hcmutnightowls.authenticationservice.service.Interface.Strategy.UserLoginStrategy;

public interface LoginStrategyProvider {
    UserLoginStrategy getStrategy(String userType);
}

package com.hcmutnightowls.authenticationservice.service.Iml;

import com.hcmutnightowls.authenticationservice.service.Interface.Provider.LoginStrategyProvider;
import com.hcmutnightowls.authenticationservice.service.Interface.Strategy.UserLoginStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginStrategyIml implements LoginStrategyProvider {
    private final ApplicationContext applicationContext;

    @Override
    public UserLoginStrategy getStrategy(String userType) {
        try {
            return (UserLoginStrategy) applicationContext.getBean(userType.toLowerCase());
        } catch (org.springframework.beans.factory.NoSuchBeanDefinitionException e) {
            throw new RuntimeException("Invalid user type: " + userType, e);
        }
    }
}

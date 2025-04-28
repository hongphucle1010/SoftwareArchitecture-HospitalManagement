package com.hcmutnightowls.authenticationservice.service.Iml;

import com.hcmutnightowls.authenticationservice.service.Interface.Provider.FinderStrategyProvider;
import com.hcmutnightowls.authenticationservice.service.Interface.Strategy.UserFinderStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FinderStrategyIml implements FinderStrategyProvider {
    private final ApplicationContext applicationContext;

    @Override
    public <T> UserFinderStrategy <T> getStrategy(String userType) {
        try {
            return (UserFinderStrategy<T>) applicationContext.getBean(userType.toLowerCase());
        } catch (org.springframework.beans.factory.NoSuchBeanDefinitionException e) {
            throw new RuntimeException("Invalid user type: " + userType, e);
        }
    }
}

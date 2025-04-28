package com.hcmutnightowls.authenticationservice.service.Interface.Provider;

import com.hcmutnightowls.authenticationservice.service.Interface.Strategy.UserFinderStrategy;

public interface FinderStrategyProvider {
    <T> UserFinderStrategy<T> getStrategy(String userType);
}

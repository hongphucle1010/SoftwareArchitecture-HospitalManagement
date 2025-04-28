package com.hcmutnightowls.authenticationservice.service;

import com.hcmutnightowls.authenticationservice.dto.request.request;
import com.hcmutnightowls.authenticationservice.service.Interface.JWT.IUserFinderService;
import com.hcmutnightowls.authenticationservice.service.Interface.Provider.FinderStrategyProvider;
import com.hcmutnightowls.authenticationservice.service.Interface.Strategy.UserFinderStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFinderService implements IUserFinderService {
    private final FinderStrategyProvider finderStrategyProvider;

    @Override
    public <T> T findUser(String userType, request loginRequest) {
        UserFinderStrategy<T> finder = finderStrategyProvider.getStrategy(userType);
        return finder.findBySubject(loginRequest.getSubject());
    }
}

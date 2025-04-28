package com.hcmutnightowls.authenticationservice.service.Admin;

import com.hcmutnightowls.authenticationservice.component.JWTCreateToken;
import com.hcmutnightowls.authenticationservice.component.JWTPasswordAuth;
import com.hcmutnightowls.authenticationservice.dto.request.request;
import com.hcmutnightowls.authenticationservice.model.Admin;
import com.hcmutnightowls.authenticationservice.service.Interface.JWT.IJWTCreateToken;
import com.hcmutnightowls.authenticationservice.service.Interface.JWT.IJWTPasswordAuth;
import com.hcmutnightowls.authenticationservice.service.Interface.JWT.IUserFinderService;
import com.hcmutnightowls.authenticationservice.service.UserFinderService;
import com.hcmutnightowls.authenticationservice.service.Interface.Strategy.UserLoginStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("admin_login")
public class AdminLogin implements UserLoginStrategy {
    @Value("${jwt.expirationtime}")
    long expiration;
    @Autowired
    private IUserFinderService iuserFinderService;

    @Autowired
    private IJWTCreateToken ijwtCreateToken;

    @Autowired
    private IJWTPasswordAuth ijwtPasswordAuth;

    @Override
    public String login(request LoginRequest) {
        Admin admin = iuserFinderService.findUser("admin_finder", LoginRequest);
        ijwtPasswordAuth.verifyOrThrow(LoginRequest.getPassword(), admin);
        return ijwtCreateToken.createJWT(
                String.valueOf(admin.getId()),
                admin.getSubject(),
                admin.getRole(),
                expiration
        );
    }
}

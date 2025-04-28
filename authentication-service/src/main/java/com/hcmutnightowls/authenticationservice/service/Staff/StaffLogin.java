package com.hcmutnightowls.authenticationservice.service.Staff;

import com.hcmutnightowls.authenticationservice.component.JWTCreateToken;
import com.hcmutnightowls.authenticationservice.component.JWTPasswordAuth;
import com.hcmutnightowls.authenticationservice.dto.request.request;
import com.hcmutnightowls.authenticationservice.model.Staff;
import com.hcmutnightowls.authenticationservice.service.Interface.JWT.IJWTCreateToken;
import com.hcmutnightowls.authenticationservice.service.Interface.JWT.IJWTPasswordAuth;
import com.hcmutnightowls.authenticationservice.service.Interface.JWT.IUserFinderService;
import com.hcmutnightowls.authenticationservice.service.UserFinderService;
import com.hcmutnightowls.authenticationservice.service.Interface.Strategy.UserLoginStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("staff_login")
public class StaffLogin implements UserLoginStrategy {
    @Value("${jwt.secret_key}")
    String secret;
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
        Staff staff = iuserFinderService.findUser("staff_finder", LoginRequest);
        ijwtPasswordAuth.verifyOrThrow(LoginRequest.getPassword(), staff);
        return ijwtCreateToken.createJWT(
                String.valueOf(staff.getId()),
                staff.getSubject(),
                staff.getRole(),
                expiration
        );
    }
}

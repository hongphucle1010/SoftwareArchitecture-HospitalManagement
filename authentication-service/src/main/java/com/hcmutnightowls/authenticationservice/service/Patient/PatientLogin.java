package com.hcmutnightowls.authenticationservice.service.Patient;

import com.hcmutnightowls.authenticationservice.component.JWTCreateToken;
import com.hcmutnightowls.authenticationservice.component.JWTPasswordAuth;
import com.hcmutnightowls.authenticationservice.dto.request.request;
import com.hcmutnightowls.authenticationservice.model.Patient;
import com.hcmutnightowls.authenticationservice.service.Interface.JWT.IJWTCreateToken;
import com.hcmutnightowls.authenticationservice.service.Interface.JWT.IJWTPasswordAuth;
import com.hcmutnightowls.authenticationservice.service.Interface.JWT.IUserFinderService;
import com.hcmutnightowls.authenticationservice.service.UserFinderService;
import com.hcmutnightowls.authenticationservice.service.Interface.Strategy.UserLoginStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("patient_login")
public class PatientLogin implements UserLoginStrategy {
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
        Patient patient = iuserFinderService.findUser("patient_finder", LoginRequest);
        ijwtPasswordAuth.verifyOrThrow(LoginRequest.getPassword(), patient);
        return ijwtCreateToken.createJWT(
                String.valueOf(patient.getId()),
                patient.getSubject(),
                patient.getRole(),
                expiration
        );
    }
}

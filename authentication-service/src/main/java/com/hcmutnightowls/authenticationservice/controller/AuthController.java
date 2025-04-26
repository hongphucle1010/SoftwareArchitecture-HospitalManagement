package com.hcmutnightowls.authenticationservice.controller;

import com.hcmutnightowls.authenticationservice.dto.request.request;
import com.hcmutnightowls.authenticationservice.dto.respond.APIRespond;
import com.hcmutnightowls.authenticationservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")

public class AuthController {
    @Autowired
    private AuthService authService;



    @PostMapping("/admin/login")
    public APIRespond<String> login(@RequestBody request LoginRequest) {
        try{
            return APIRespond.<String>builder()
                    .status(200)
                    .message(authService.loginAdmin(LoginRequest))
                    .build();
        } catch (Exception e) {
            return APIRespond.<String>builder()
                    .status(401)
                    .message("An error occurred: " + e.getMessage())
                    .build();
        }

    }
    @PostMapping("/staff/login")
    public APIRespond<String> loginStaff(@RequestBody request LoginRequest) {
        try {
            return APIRespond.<String>builder()
                    .status(200)
                    .message(authService.loginStaff(LoginRequest))
                    .build();
        } catch (Exception e) {
            return APIRespond.<String>builder()
                    .status(401)
                    .message("An error occurred: " + e.getMessage())
                    .build();
        }
    }
    @PostMapping("/patient/login")
    public APIRespond<String> loginPatient(@RequestBody request LoginRequest) {
        try {
            return APIRespond.<String>builder()
                    .status(200)
                    .message(authService.loginPatient(LoginRequest))
                    .build();
        } catch (Exception e) {
            return APIRespond.<String>builder()
                    .status(401)
                    .message("An error occurred: " + e.getMessage())
                    .build();
        }
    }

}

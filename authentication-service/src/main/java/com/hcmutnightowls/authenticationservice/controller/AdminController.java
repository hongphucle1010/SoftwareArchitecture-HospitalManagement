package com.hcmutnightowls.authenticationservice.controller;

import com.hcmutnightowls.authenticationservice.dto.respond.APIRespond;
import com.hcmutnightowls.authenticationservice.model.Admin;
import com.hcmutnightowls.authenticationservice.service.AdminService;
import com.hcmutnightowls.authenticationservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;


import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AdminService adminService;
//   `@PreAuthorize("hasRole('ADMIN')")`
    @GetMapping("/admin/all")
    public APIRespond<List<Admin>> getAll(Authentication authentication) {
        System.out.println(authentication);
        try {
            return APIRespond.<List<Admin>>builder()
                    .status(200)
                    .data(adminService.getAll())
                    .message("success")
                    .build();
        } catch (Exception e) {
            return APIRespond.<List<Admin>>builder()
                    .status(401)
                    .message("An error occurred: " + e.getMessage())
                    .build();
        }
    }
}

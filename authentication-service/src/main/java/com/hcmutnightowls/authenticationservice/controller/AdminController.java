package com.hcmutnightowls.authenticationservice.controller;

import com.hcmutnightowls.authenticationservice.dto.respond.APIRespond;
import com.hcmutnightowls.authenticationservice.model.Admin;
import com.hcmutnightowls.authenticationservice.service.Admin.AdminService;
import com.hcmutnightowls.authenticationservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;


import java.util.List;

@RequestMapping("/api/admin")
@RestController
public class AdminController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AdminService adminService;
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all admins")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/all")
    public ResponseEntity<APIRespond<List<Admin>>> getAll(Authentication authentication) {
        System.out.println(authentication);
        try {
            return ResponseEntity.ok(APIRespond.<List<Admin>>builder()
                    .status(200)
                    .data(adminService.getAll())
                    .message("success")
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(401).body(
                    APIRespond.<List<Admin>>builder()
                    .status(401)
                    .message("An error occurred: " + e.getMessage())
                    .build());
        }
    }
}

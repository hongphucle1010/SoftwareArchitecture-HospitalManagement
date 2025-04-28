package com.hcmutnightowls.authenticationservice.controller;

import com.hcmutnightowls.authenticationservice.dto.request.request;
import com.hcmutnightowls.authenticationservice.dto.respond.APIRespond;
import com.hcmutnightowls.authenticationservice.service.Admin.AdminService;
import com.hcmutnightowls.authenticationservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")

public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AdminService adminService;


    @Operation(summary = "Login for admin")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("/admin/login")
    public ResponseEntity<APIRespond<String>> login(@RequestBody request LoginRequest) {
        try {
            return ResponseEntity.ok(APIRespond.<String>builder()
                    .status(200)
                    .message(authService.login("admin_login", LoginRequest))
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(401).body(
                    APIRespond.<String>builder()
                    .status(401)
                    .message("An error occurred: " + e.getMessage())
                    .build());
        }
    }

    @Operation(summary = "Login for staff")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("/staff/login")
    public ResponseEntity<APIRespond<String>> loginStaff(@RequestBody request LoginRequest) {
        try {
            return ResponseEntity.ok(APIRespond.<String>builder()
                    .status(200)
                    .message(authService.login("staff_login", LoginRequest))
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(401).body(
                    APIRespond.<String>builder()
                    .status(401)
                    .message("An error occurred: " + e.getMessage())
                    .build());
        }
    }

    @Operation(summary = "Login for patient")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("/patient/login")
    public ResponseEntity<APIRespond<String>> loginPatient(@RequestBody request LoginRequest) {
        try {
            return ResponseEntity.ok(APIRespond.<String>builder()
                    .status(200)
                    .message(authService.login("patient_login", LoginRequest))
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(401).body(
                    APIRespond.<String>builder()
                    .status(401)
                    .message("An error occurred: " + e.getMessage())
                    .build());
        }
    }

}

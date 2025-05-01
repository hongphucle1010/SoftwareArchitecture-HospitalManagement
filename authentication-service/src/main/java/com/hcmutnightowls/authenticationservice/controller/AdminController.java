package com.hcmutnightowls.authenticationservice.controller;

import com.hcmutnightowls.authenticationservice.dto.request.request;
import com.hcmutnightowls.authenticationservice.dto.request.requestRegister;
import com.hcmutnightowls.authenticationservice.dto.respond.APIRespond;
import com.hcmutnightowls.authenticationservice.model.Admin;
import com.hcmutnightowls.authenticationservice.service.Interface.Admin.IAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;


import java.util.List;

@RequestMapping("/api/admin")
@RestController
public class AdminController {
    @Autowired
    private IAdminService iadminService;
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all admins")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/all")
    public ResponseEntity<APIResponse<List<Admin>>> getAll(Authentication authentication) {
        System.out.println(authentication);
        try {
            return ResponseEntity.ok(APIResponse.<List<Admin>>builder()
                    .status(200)
                    .data(iadminService.getAll())
                    .message("success")
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(401).body(
                    APIResponse.<List<Admin>>builder()
                    .status(401)
                    .message("An error occurred: " + e.getMessage())
                    .build());
        }
    }

    @Operation(summary = "POST current admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping
    public Void postAdmin(@RequestBody requestRegister req) {
        try {
             ResponseEntity.ok(APIRespond.<Admin>builder()
                    .status(200)
                    .data(iadminService.postAdmin(req))
                    .message("success")
                    .build());
        } catch (Exception e) {
             ResponseEntity.status(401).body(
                    APIRespond.<Admin>builder()
                    .status(401)
                    .message("An error occurred: " + e.getMessage())
                    .build());
        }
        return null;
    }
}

package com.hcmutnightowls.authenticationservice.controller;

import com.hcmutnightowls.authenticationservice.dto.request.request;
import com.hcmutnightowls.authenticationservice.dto.response.APIResponse;
import com.hcmutnightowls.authenticationservice.model.Staff;
import com.hcmutnightowls.authenticationservice.service.Interface.Staff.IStaffService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
    @Autowired
    private IStaffService istaffService;

    @Operation(summary = "POST current staff")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping
    public ResponseEntity<APIResponse<Staff>> postStaff(@RequestBody request req) {
        try {
            return ResponseEntity.ok(APIResponse.<Staff>builder()
                    .status(200)
                    .data(istaffService.postStaff(req))
                    .message("success")
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(401).body(
                    APIResponse.<Staff>builder()
                            .status(401)
                            .message("An error occurred: " + e.getMessage())
                            .build());
        }
    }
}

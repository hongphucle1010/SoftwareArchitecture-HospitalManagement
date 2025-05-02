package com.hcmutnightowls.authenticationservice.controller;
import com.hcmutnightowls.authenticationservice.dto.request.request;
import com.hcmutnightowls.authenticationservice.dto.request.requestRegister;
import com.hcmutnightowls.authenticationservice.dto.response.APIResponse;
import com.hcmutnightowls.authenticationservice.model.Admin;
import com.hcmutnightowls.authenticationservice.model.Patient;
import com.hcmutnightowls.authenticationservice.service.Interface.Patient.IPatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    private IPatientService iPatientService;

    @Operation(summary = "POST current patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping
    public Void postPatient(@RequestBody requestRegister req) {
        try {
            ResponseEntity.ok(APIResponse.<Patient>builder()
                    .status(200)
                    .message("success")
                    .build());
        } catch (Exception e) {
            ResponseEntity.status(401).body(
                    APIResponse.<Patient>builder()
                            .status(401)
                            .message("An error occurred: " + e.getMessage())
                            .build());
        }
        return null;
    }
}

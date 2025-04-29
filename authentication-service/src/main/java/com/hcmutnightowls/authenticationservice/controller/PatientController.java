package com.hcmutnightowls.authenticationservice.controller;
import com.hcmutnightowls.authenticationservice.dto.request.request;
import com.hcmutnightowls.authenticationservice.dto.respond.APIRespond;
import com.hcmutnightowls.authenticationservice.model.Admin;
import com.hcmutnightowls.authenticationservice.model.Patient;
import com.hcmutnightowls.authenticationservice.model.Staff;
import com.hcmutnightowls.authenticationservice.service.Admin.AdminService;
import com.hcmutnightowls.authenticationservice.service.AuthService;
import com.hcmutnightowls.authenticationservice.service.Interface.Admin.IAdminService;
import com.hcmutnightowls.authenticationservice.service.Interface.Patient.IPatientService;
import com.hcmutnightowls.authenticationservice.service.Interface.Staff.IStaffService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.ws.rs.POST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<APIRespond<Patient>> postPatient(@RequestBody request req) {
        try {
            return ResponseEntity.ok(APIRespond.<Patient>builder()
                    .status(200)
                    .data(iPatientService.postPatient(req))
                    .message("success")
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(401).body(
                    APIRespond.<Patient>builder()
                            .status(401)
                            .message("An error occurred: " + e.getMessage())
                            .build());
        }
    }
}

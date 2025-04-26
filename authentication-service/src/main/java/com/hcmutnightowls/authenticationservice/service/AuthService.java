package com.hcmutnightowls.authenticationservice.service;

import com.hcmutnightowls.authenticationservice.component.JwtAuthen;
import com.hcmutnightowls.authenticationservice.dto.request.request;
import com.hcmutnightowls.authenticationservice.model.Admin;
import com.hcmutnightowls.authenticationservice.model.Patient;
import com.hcmutnightowls.authenticationservice.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthService authService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private JwtAuthen jwtAuthen;

    public String loginAdmin(request LoginRequest) {
        Admin admin = adminService.findBySubject(LoginRequest.getPhone()).orElseThrow(() -> new RuntimeException("Admin not found"));
        return jwtAuthen.authentication(LoginRequest.getPassword(), admin);
    }

    public String loginStaff(request loginRequest) {
        Staff staff = staffService.findBySubject(loginRequest.getPhone()).orElseThrow(() -> new RuntimeException("Staff not found"));
        return jwtAuthen.authentication(loginRequest.getPassword(), staff);
    }

    public String loginPatient(request loginRequest) {
        Patient patient = patientService.findBySubject(loginRequest.getPhone()).orElseThrow(() -> new RuntimeException("Patient not found"));
        return jwtAuthen.authentication(loginRequest.getPassword(), patient);
    }
}

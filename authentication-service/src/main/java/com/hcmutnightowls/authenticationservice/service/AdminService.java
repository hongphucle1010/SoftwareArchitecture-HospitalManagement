package com.hcmutnightowls.authenticationservice.service;

import com.hcmutnightowls.authenticationservice.model.Admin;
import com.hcmutnightowls.authenticationservice.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepo adminRepo;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Optional<Admin> findBySubject(String phone) {
        return adminRepo.findBySubject(phone);
    }
}